package com.sunpowder.douch.player;

import io.netty.channel.Channel;
import io.netty.buffer.ByteBuf;

public class PlayerConnection {
    private final Channel channel;
    public PlayerConnection(Channel channel) {
        this.channel = channel;
    }
    public void sendPacket(ByteBuf buf) {
        channel.writeAndFlush(buf);
    }
    public void close() {
        channel.close();
    }
    public boolean isActive() {
        return channel.isActive();
    }
}
