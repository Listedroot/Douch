package com.sunpowder.douch.network;

import io.netty.channel.Channel;

public class MCConnection {
    private final Channel channel;
    public MCConnection(Channel channel) {
        this.channel = channel;
    }
    public Channel getChannel() { return channel; }
    public void close() { channel.close(); }
    public boolean isActive() { return channel.isActive(); }
}
