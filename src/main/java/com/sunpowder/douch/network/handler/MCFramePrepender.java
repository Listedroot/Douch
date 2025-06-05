package com.sunpowder.douch.network.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class MCFramePrepender extends MessageToByteEncoder<ByteBuf> {
    @Override
    protected void encode(ChannelHandlerContext ctx, ByteBuf msg, ByteBuf out) throws Exception {
        int length = msg.readableBytes();
        
        // Write the length as a VarInt
        ByteBuf lengthBuf = ctx.alloc().buffer(5); // Max size of a VarInt
        writeVarInt(lengthBuf, length);
        
        // Write the length and the message
        out.writeBytes(lengthBuf);
        out.writeBytes(msg);
        
        // Release the temporary buffer
        lengthBuf.release();
    }
    
    private static void writeVarInt(ByteBuf out, int value) {
        while ((value & 0xFFFFFF80) != 0) {
            out.writeByte((value & 0x7F) | 0x80);
            value >>>= 7;
        }
        out.writeByte(value);
    }
}
