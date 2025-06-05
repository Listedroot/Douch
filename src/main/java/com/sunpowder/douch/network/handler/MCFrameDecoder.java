package com.sunpowder.douch.network.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.ByteProcessor;

import java.util.List;

public class MCFrameDecoder extends ByteToMessageDecoder {
    private static final int MAX_FRAME_LENGTH = 2097152; // 2MB max packet size
    private static final int LENGTH_FIELD_LENGTH = 3; // 3 bytes for length (up to 2MB)
    
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        in.markReaderIndex();
        
        // Read the length of the packet
        int length = readVarInt(in);
        
        if (length < 0) {
            throw new IllegalStateException("Invalid packet length: " + length);
        }
        
        if (length > MAX_FRAME_LENGTH) {
            throw new IllegalStateException("Packet too large: " + length + " > " + MAX_FRAME_LENGTH);
        }
        
        // If we don't have enough bytes yet, wait for more
        if (in.readableBytes() < length) {
            in.resetReaderIndex();
            return;
        }
        
        // We have a complete packet, slice it and add to the output
        out.add(in.readRetainedSlice(length));
    }
    
    private static int readVarInt(ByteBuf buf) {
        int result = 0;
        int bytes = 0;
        byte current;
        
        do {
            current = buf.readByte();
            result |= (current & 0x7F) << (bytes++ * 7);
            
            if (bytes > 5) {
                throw new RuntimeException("VarInt too big");
            }
        } while ((current & 0x80) == 0x80);
        
        return result;
    }
}
