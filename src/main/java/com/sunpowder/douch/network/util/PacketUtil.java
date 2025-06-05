package com.sunpowder.douch.network.util;

import io.netty.buffer.ByteBuf;
import io.netty.util.CharsetUtil;

public class PacketUtil {
    public static String readString(ByteBuf buf) {
        int length = VarIntUtil.readVarInt(buf);
        byte[] bytes = new byte[length];
        buf.readBytes(bytes);
        return new String(bytes, CharsetUtil.UTF_8);
    }
    
    public static void writeString(ByteBuf buf, String str) {
        byte[] bytes = str.getBytes(CharsetUtil.UTF_8);
        VarIntUtil.writeVarInt(buf, bytes.length);
        buf.writeBytes(bytes);
    }
    
    public static byte[] readByteArray(ByteBuf buf) {
        int length = VarIntUtil.readVarInt(buf);
        byte[] bytes = new byte[length];
        buf.readBytes(bytes);
        return bytes;
    }
    
    public static void writeByteArray(ByteBuf buf, byte[] bytes) {
        VarIntUtil.writeVarInt(buf, bytes.length);
        buf.writeBytes(bytes);
    }
}
