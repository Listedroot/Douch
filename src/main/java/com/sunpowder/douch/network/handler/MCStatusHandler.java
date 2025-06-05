package com.sunpowder.douch.network.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MCStatusHandler extends SimpleChannelInboundHandler<Object> {
    private final int protocolVersion;
    private boolean responded = false;

    public MCStatusHandler(int protocolVersion) {
        this.protocolVersion = protocolVersion;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (!responded) {
            // Respond with a basic status JSON
            String json = "{\"version\":{\"name\":\"Douch\",\"protocol\":" + protocolVersion + "},\"players\":{\"max\":100,\"online\":0},\"description\":{\"text\":\"Douch Proxy by Sunpowder\"}}";
            sendPacket(ctx, 0x00, json);
            responded = true;
        }
    }

    private void sendPacket(ChannelHandlerContext ctx, int packetId, String json) {
        byte[] jsonBytes = json.getBytes();
        int length = varIntSize(packetId) + varIntSize(jsonBytes.length) + jsonBytes.length;
        ByteBuf buf = Unpooled.buffer(varIntSize(length) + length);
        writeVarInt(buf, length);
        writeVarInt(buf, packetId);
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
