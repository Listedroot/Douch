package com.sunpowder.douch.network.packet.login;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import com.sunpowder.douch.network.util.PacketUtil;

import java.util.UUID;

public class ClientboundLoginSuccessPacket {
    private final UUID uuid;
    private final String username;
    
    public ClientboundLoginSuccessPacket(UUID uuid, String username) {
        this.uuid = uuid;
        this.username = username;
    }
    
    public ByteBuf encode() {
        ByteBuf buf = ByteBufAllocator.DEFAULT.buffer();
        
        // Write UUID as string (dashless)
        String uuidStr = uuid.toString().replace("-", "");
        PacketUtil.writeString(buf, uuidStr);
        
        // Write username
        PacketUtil.writeString(buf, username);
        
        // Write properties array (empty for now)
        buf.writeInt(0);
        
        return buf;
    }
}
