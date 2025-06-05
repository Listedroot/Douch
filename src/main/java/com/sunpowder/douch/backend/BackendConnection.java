package com.sunpowder.douch.backend;

import io.netty.channel.Channel;

public class BackendConnection {
    private final Channel channel;
    private final BackendServer server;
    public BackendConnection(Channel channel, BackendServer server) {
        this.channel = channel;
        this.server = server;
    }
    public Channel getChannel() { return channel; }
    public BackendServer getServer() { return server; }
}
