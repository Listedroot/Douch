package com.sunpowder.douch.network.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import javax.crypto.Cipher;
import java.util.List;

public class MCNettyDecrypt extends ByteToMessageDecoder {
    private final Cipher cipher;

    public MCNettyDecrypt(Cipher cipher) {
        this.cipher = cipher;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        byte[] input = new byte[in.readableBytes()];
        in.readBytes(input);
        byte[] output = cipher.update(input);
        if (output != null) {
            out.add(ctx.alloc().buffer(output.length).writeBytes(output));
        }
    }
}
