package com.sunpowder.douch.network;

import io.netty.channel.ChannelHandlerContext;
import io.netty.buffer.ByteBuf;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MCPacketDispatcher {
    private final Map<Integer, PacketHandler> handlers = new ConcurrentHashMap<>();
    public void register(int packetId, PacketHandler handler) {
        handlers.put(packetId, handler);
    }
    public void dispatch(int packetId, ChannelHandlerContext ctx, ByteBuf buf) {
        PacketHandler handler = handlers.get(packetId);
        if (handler != null) handler.handle(ctx, buf);
    }
    public interface PacketHandler {
        void handle(ChannelHandlerContext ctx, ByteBuf buf);
    }
}
