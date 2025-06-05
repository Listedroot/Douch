package com.sunpowder.douch.network.packet.login;

import io.netty.buffer.ByteBuf;
import io.netty.util.CharsetUtil;

public class ServerboundLoginStartPacket {
    public final String username;
    public final boolean hasSigData;
    public final long timestamp;
    public final byte[] publicKey;
    public final byte[] signature;
    public final boolean hasUUID;
    
    public ServerboundLoginStartPacket(ByteBuf buf) {
        this.username = readString(buf);
        this.hasSigData = buf.readBoolean();
        if (hasSigData) {
            this.timestamp = buf.readLong();
            int keyLength = readVarInt(buf);
            this.publicKey = new byte[keyLength];
            buf.readBytes(publicKey);
            int sigLength = readVarInt(buf);
            this.signature = new byte[sigLength];
            buf.readBytes(signature);
        } else {
            this.timestamp = 0;
            this.publicKey = new byte[0];
            this.signature = new byte[0];
        }
        this.hasUUID = buf.readBoolean();
        if (hasUUID) {
            // Skip UUID for now
            buf.skipBytes(16);
        }
    }
    
    private static String readString(ByteBuf buf) {
        int length = readVarInt(buf);
        byte[] bytes = new byte[length];
        buf.readBytes(bytes);
        return new String(bytes, CharsetUtil.UTF_8);
    }
    
    private static int readVarInt(ByteBuf buf) {
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
}
