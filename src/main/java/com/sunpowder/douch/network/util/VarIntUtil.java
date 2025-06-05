package com.sunpowder.douch.network.util;

import io.netty.buffer.ByteBuf;

public class VarIntUtil {
    public static int readVarInt(ByteBuf buf) {
        int value = 0;
        int length = 0;
        byte current;
        do {
            current = buf.readByte();
            value |= (current & 0x7F) << (length++ * 7);
            if (length > 5) {
                throw new RuntimeException("VarInt too big");
            }
        } while ((current & 0x80) == 0x80);
        return value;
    }
    
    public static void writeVarInt(ByteBuf buf, int value) {
        while ((value & 0xFFFFFF80) != 0L) {
            buf.writeByte((value & 0x7F) | 0x80);
            value >>>= 7;
        }
        buf.writeByte(value & 0x7F);
    }
    
    public static int getVarIntSize(int value) {
        int size = 0;
        do {
            value >>>= 7;
            size++;
        } while (value != 0);
        return size;
    }
}
