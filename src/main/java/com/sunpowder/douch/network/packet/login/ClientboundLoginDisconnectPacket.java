package com.sunpowder.douch.network.packet.login;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import com.sunpowder.douch.network.util.PacketUtil;

public class ClientboundLoginDisconnectPacket {
    private final String reason;
    
    public ClientboundLoginDisconnectPacket(String reason) {
        this.reason = reason;
    }
    
    public ByteBuf encode() {
        JsonObject json = new JsonObject();
        json.addProperty("text", reason);
        String jsonStr = json.toString();
        
        ByteBuf buf = ByteBufAllocator.DEFAULT.buffer();
        PacketUtil.writeString(buf, jsonStr);
        return buf;
    }
    
    public static ClientboundLoginDisconnectPacket fromJson(String json) {
        JsonObject obj = JsonParser.parseString(json).getAsJsonObject();
        return new ClientboundLoginDisconnectPacket(obj.get("text").getAsString());
    }
}
