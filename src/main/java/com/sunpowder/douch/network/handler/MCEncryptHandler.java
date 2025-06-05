package com.sunpowder.douch.network.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import javax.crypto.Cipher;

public class MCEncryptHandler extends MessageToByteEncoder<ByteBuf> {
    private final Cipher cipher;
    private byte[] buffer = new byte[0];
    
    public MCEncryptHandler(Cipher cipher) {
        this.cipher = cipher;
    }
    
    @Override
    protected void encode(ChannelHandlerContext ctx, ByteBuf msg, ByteBuf out) throws Exception {
        int length = msg.readableBytes();
        byte[] bytes = getBytes(msg);
        
        int outputSize = cipher.getOutputSize(length);
        if (buffer.length < outputSize) {
            buffer = new byte[outputSize];
        }
        
        int outputLength = cipher.update(bytes, 0, length, buffer, 0);
        if (outputLength > 0) {
            out.writeBytes(buffer, 0, outputLength);
        }
    }
    
    private byte[] getBytes(ByteBuf buf) {
        byte[] bytes = new byte[buf.readableBytes()];
        int readerIndex = buf.readerIndex();
        buf.getBytes(readerIndex, bytes);
        return bytes;
    }
}
