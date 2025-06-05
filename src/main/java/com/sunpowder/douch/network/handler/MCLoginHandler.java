package com.sunpowder.douch.network.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MCLoginHandler extends SimpleChannelInboundHandler<Object> {
    private static final Logger logger = LoggerFactory.getLogger(MCLoginHandler.class);
    private final int protocolVersion;

    public MCLoginHandler(int protocolVersion) {
        this.protocolVersion = protocolVersion;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        // For demonstration, immediately disconnect with a message
        String json = "{\"text\":\"Douch Proxy: Authentication not implemented yet.\"}";
        sendDisconnect(ctx, json);
        ctx.close();
    }

    private void sendDisconnect(ChannelHandlerContext ctx, String json) {
        byte[] jsonBytes = json.getBytes();
        int length = varIntSize(0x00) + varIntSize(jsonBytes.length) + jsonBytes.length;
        ByteBuf buf = Unpooled.buffer(varIntSize(length) + length);
        writeVarInt(buf, length);
        writeVarInt(buf, 0x00); // Disconnect packet id
        writeVarInt(buf, jsonBytes.length);
        buf.writeBytes(jsonBytes);
        ctx.writeAndFlush(buf);
    }

    private void writeVarInt(ByteBuf buf, int value) {
        while ((value & 0xFFFFFF80) != 0L) {
            buf.writeByte((value & 0x7F) | 0x80);
            value >>>= 7;
        }
        buf.writeByte(value & 0x7F);
    }

    private int varIntSize(int value) {
        int size = 0;
        do {
            value >>>= 7;
            size++;
        } while (value != 0);
        return size;
    }
}
