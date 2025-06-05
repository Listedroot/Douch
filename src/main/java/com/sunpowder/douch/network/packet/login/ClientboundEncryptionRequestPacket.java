package com.sunpowder.douch.network.packet.login;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import com.sunpowder.douch.network.util.PacketUtil;

import java.security.PublicKey;

public class ClientboundEncryptionRequestPacket {
    private final String serverId;
    private final PublicKey publicKey;
    private final byte[] verifyToken;
    
    public ClientboundEncryptionRequestPacket(String serverId, PublicKey publicKey, byte[] verifyToken) {
        this.serverId = serverId;
        this.publicKey = publicKey;
        this.verifyToken = verifyToken;
    }
    
    public ByteBuf encode() {
        ByteBuf buf = ByteBufAllocator.DEFAULT.buffer();
        
        // Server ID (empty for offline mode, but still required)
        PacketUtil.writeString(buf, serverId);
        
        // Public Key
        byte[] encodedKey = publicKey.getEncoded();
        PacketUtil.writeByteArray(buf, encodedKey);
        
        // Verify Token
        PacketUtil.writeByteArray(buf, verifyToken);
        
        return buf;
    }
}
