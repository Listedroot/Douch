package com.sunpowder.douch.network.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import javax.crypto.Cipher;

public class MCNettyEncrypt extends MessageToByteEncoder<ByteBuf> {
    private final Cipher cipher;

    public MCNettyEncrypt(Cipher cipher) {
        this.cipher = cipher;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, ByteBuf msg, ByteBuf out) throws Exception {
        byte[] input = new byte[msg.readableBytes()];
        msg.readBytes(input);
        byte[] output = cipher.update(input);
        if (output != null) {
            out.writeBytes(output);
        }
    }
}
