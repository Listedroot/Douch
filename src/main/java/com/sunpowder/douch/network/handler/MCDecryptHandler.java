package com.sunpowder.douch.network.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import javax.crypto.Cipher;
import java.util.List;

public class MCDecryptHandler extends ByteToMessageDecoder {
    private final Cipher cipher;
    private byte[] buffer = new byte[0];
    
    public MCDecryptHandler(Cipher cipher) {
        this.cipher = cipher;
    }
    
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        int length = in.readableBytes();
        if (length == 0) {
            return;
        }
        
        byte[] input = new byte[length];
        in.readBytes(input);
        
        int outputSize = cipher.getOutputSize(length);
        if (buffer.length < outputSize) {
            buffer = new byte[outputSize];
        }
        
        int outputLength = cipher.update(input, 0, length, buffer, 0);
        if (outputLength > 0) {
            out.add(Unpooled.wrappedBuffer(buffer, 0, outputLength));
        }
    }
}
