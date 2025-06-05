package com.sunpowder.douch.network.handler;

import com.google.gson.JsonObject;
import com.sunpowder.douch.core.DouchProxy;
import com.sunpowder.douch.auth.SessionService;
import com.sunpowder.douch.network.LoginSession;
import com.sunpowder.douch.network.packet.login.*;
import com.sunpowder.douch.network.util.EncryptionUtil;
import com.sunpowder.douch.network.util.PacketUtil;
import com.sunpowder.douch.network.util.VarIntUtil;
import com.sunpowder.douch.player.PlayerAuthenticator;
import com.sunpowder.douch.player.PlayerConnection;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.AttributeKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MCLoginHandler extends SimpleChannelInboundHandler<ByteBuf> {
    private static final Logger logger = LoggerFactory.getLogger(MCLoginHandler.class);
    private static final AttributeKey<LoginSession> SESSION_KEY = AttributeKey.valueOf("loginSession");
    private static final ExecutorService AUTH_POOL = Executors.newCachedThreadPool();
    
    private final int protocolVersion;
    private final PlayerAuthenticator authenticator;
    private final boolean onlineMode;
    private final DouchProxy proxy;
    
    public MCLoginHandler(int protocolVersion, PlayerAuthenticator authenticator, boolean onlineMode, DouchProxy proxy) {
        this.protocolVersion = protocolVersion;
        this.authenticator = authenticator;
        this.onlineMode = onlineMode;
        this.proxy = proxy;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        LoginSession session = new LoginSession(ctx.channel());
        ctx.channel().attr(SESSION_KEY).set(session);
        logger.info("New connection from {}", ctx.channel().remoteAddress());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf buf) throws Exception {
        int packetId = VarIntUtil.readVarInt(buf);
        
        switch (packetId) {
            case 0x00: // Login Start
                handleLoginStart(ctx, buf);
                break;
            case 0x01: // Encryption Response
                handleEncryptionResponse(ctx, buf);
                break;
            default:
                logger.warn("Received unknown login packet ID: {}", packetId);
                ctx.close();
        }
    }
    
    private void handleLoginStart(ChannelHandlerContext ctx, ByteBuf buf) {
        try {
            LoginSession session = ctx.channel().attr(SESSION_KEY).get();
            ServerboundLoginStartPacket loginStart = new ServerboundLoginStartPacket(buf);
            session.setUsername(loginStart.username);
            
            if (onlineMode) {
                // In online mode, request encryption
                startEncryptionHandshake(ctx, session);
            } else {
                // In offline mode, proceed with login immediately
                completeLogin(ctx, session, null);
            }
        } catch (Exception e) {
            logger.error("Error handling login start", e);
            disconnect(ctx, "Internal server error");
        }
    }
    
    private void startEncryptionHandshake(ChannelHandlerContext ctx, LoginSession session) throws Exception {
        KeyPair keyPair = EncryptionUtil.generateKeyPair();
        session.setKeyPair(keyPair);
        
        // Send encryption request
        ClientboundEncryptionRequestPacket encryptionRequest = new ClientboundEncryptionRequestPacket(
                "", // Server ID (empty for online mode)
                keyPair.getPublic(),
                session.getVerifyToken()
        );
        
        sendPacket(ctx, 0x01, encryptionRequest.encode());
    }
    
    private void handleEncryptionResponse(ChannelHandlerContext ctx, ByteBuf buf) {
        LoginSession session = ctx.channel().attr(SESSION_KEY).get();
        if (session == null) {
            ctx.close();
            return;
        }
        
        AUTH_POOL.execute(() -> handleEncryptionResponseAsync(ctx, session, buf));
    }
    
    private void handleEncryptionResponseAsync(ChannelHandlerContext ctx, LoginSession session, ByteBuf buf) {
        try {
            ServerboundEncryptionResponsePacket response = new ServerboundEncryptionResponsePacket(buf);
            
            // Decrypt the shared secret and verify token
            PrivateKey privateKey = session.getKeyPair().getPrivate();
            byte[] sharedSecret = EncryptionUtil.decryptRSA(privateKey, response.sharedSecret);
            byte[] verifyToken = EncryptionUtil.decryptRSA(privateKey, response.verifyToken);
            
            // Verify the token
            if (!session.verifyToken(verifyToken)) {
                disconnect(ctx, "Invalid verify token");
                return;
            }
            
            // Set up encryption
            SecretKey secretKey = new javax.crypto.spec.SecretKeySpec(sharedSecret, "AES");
            session.setSecretKey(secretKey);
            
            // Complete login with encryption
            completeLogin(ctx, session, secretKey);
        } catch (Exception e) {
            logger.error("Error handling encryption response", e);
            disconnect(ctx, "Encryption error");
        }
    }
    
    private void completeLogin(ChannelHandlerContext ctx, LoginSession session, SecretKey secretKey) {
        try {
            // In a real implementation, you would verify the session with Mojang's servers here
            // For now, we'll just complete the login
            
            // Create player connection
            PlayerConnection connection = new PlayerConnection(ctx.channel());
            
            // Send login success
            UUID playerUuid = authenticator.getUUIDOffline(session.getUsername());
            ClientboundLoginSuccessPacket successPacket = new ClientboundLoginSuccessPacket(
                    playerUuid,
                    session.getUsername()
            );
            sendPacket(ctx, 0x02, successPacket.encode());
            
            // Set up encryption if needed
            if (secretKey != null) {
                enableEncryption(ctx, secretKey);
            }
            
            // Switch to play state
            // In a real implementation, you would create a Player object and add it to your player list
            logger.info("Player {} logged in with UUID {}", session.getUsername(), playerUuid);
            
            // Here you would typically switch to the play state
            // ctx.pipeline().replace("login-handler", "play-handler", new PlayHandler(proxy, connection));
            
        } catch (Exception e) {
            logger.error("Error completing login", e);
            disconnect(ctx, "Internal server error");
        }
    }
    
    private void enableEncryption(ChannelHandlerContext ctx, SecretKey key) {
        try {
            ctx.pipeline().addBefore("framer", "decrypt", new MCDecryptHandler(EncryptionUtil.createNetCipher(Cipher.DECRYPT_MODE, key)));
            ctx.pipeline().addBefore("framer", "encrypt", new MCEncryptHandler(EncryptionUtil.createNetCipher(Cipher.ENCRYPT_MODE, key)));
        } catch (Exception e) {
            throw new RuntimeException("Failed to enable encryption", e);
        }
    }
    
    private void sendPacket(ChannelHandlerContext ctx, int packetId, ByteBuf data) {
        ByteBuf header = ctx.alloc().buffer(VarIntUtil.getVarIntSize(packetId + VarIntUtil.getVarIntSize(data.readableBytes())));
        VarIntUtil.writeVarInt(header, packetId);
        ctx.writeAndFlush(ctx.alloc().compositeBuffer(2).addComponents(true, header, data));
    }
    
    private void disconnect(ChannelHandlerContext ctx, String reason) {
        ClientboundLoginDisconnectPacket disconnectPacket = new ClientboundLoginDisconnectPacket(reason);
        sendPacket(ctx, 0x00, disconnectPacket.encode());
        ctx.close();
    }
    
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        logger.error("Error in login handler", cause);
        ctx.close();
    }
}
