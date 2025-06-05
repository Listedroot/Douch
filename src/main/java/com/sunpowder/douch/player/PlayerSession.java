package com.sunpowder.douch.player;

import java.util.UUID;
import java.net.InetSocketAddress;
import io.netty.channel.Channel;

public class PlayerSession {
    private final String username;
    private final UUID uuid;
    private final InetSocketAddress address;
    private final Channel channel;
    private boolean online;

    public PlayerSession(String username, UUID uuid, InetSocketAddress address, Channel channel) {
        this.username = username;
        this.uuid = uuid;
        this.address = address;
        this.channel = channel;
        this.online = true;
    }

    public String getUsername() { return username; }
    public UUID getUuid() { return uuid; }
    public InetSocketAddress getAddress() { return address; }
    public Channel getChannel() { return channel; }
    public boolean isOnline() { return online; }
    public void disconnect(String reason) {
        if (online) {
            channel.close();
            online = false;
        }
    }
}
