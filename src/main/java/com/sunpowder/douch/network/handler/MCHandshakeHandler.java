package com.sunpowder.douch.network.handler;

import com.sunpowder.douch.core.DouchProxy;
import com.sunpowder.douch.network.NetworkManager;
import com.sunpowder.douch.player.PlayerAuthenticator;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MCHandshakeHandler extends SimpleChannelInboundHandler<MCHandshakeDecoder.HandshakePacket> {
    private static final Logger logger = LoggerFactory.getLogger(MCHandshakeHandler.class);
    
    private final DouchProxy proxy;
    private final boolean onlineMode;
    
    public MCHandshakeHandler(DouchProxy proxy, boolean onlineMode) {
        this.proxy = proxy;
        this.onlineMode = onlineMode;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MCHandshakeDecoder.HandshakePacket msg) {
        logger.info("Received handshake: version={}, address={}, port={}, nextState={}",
                msg.protocolVersion, msg.serverAddress, msg.serverPort, msg.nextState);
        
        // Store protocol version in the channel for later use
        ctx.channel().attr(NetworkManager.PROTOCOL_VERSION).set(msg.protocolVersion);
        
        // Version detection and pipeline switching
        if (msg.nextState == 1) {
            // Status request
            ctx.pipeline().replace(this, "status-handler", new MCStatusHandler(msg.protocolVersion));
        } else if (msg.nextState == 2) {
            // Login request
            PlayerAuthenticator authenticator = new PlayerAuthenticator();
            MCLoginHandler loginHandler = new MCLoginHandler(msg.protocolVersion, authenticator, onlineMode, proxy);
            ctx.pipeline().replace(this, "login-handler", loginHandler);
        } else {
            logger.error("Unknown next state: {}", msg.nextState);
            ctx.close();
        }
    }
    
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        logger.error("Error in handshake handler", cause);
        ctx.close();
    }
}
