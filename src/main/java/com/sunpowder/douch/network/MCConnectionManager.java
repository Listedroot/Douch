package com.sunpowder.douch.network;

import io.netty.channel.Channel;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MCConnectionManager {
    private final Map<String, MCConnection> connections = new ConcurrentHashMap<>();
    public void addConnection(String username, Channel channel) {
        connections.put(username.toLowerCase(), new MCConnection(channel));
    }
    public void removeConnection(String username) {
        connections.remove(username.toLowerCase());
    }
    public MCConnection getConnection(String username) {
        return connections.get(username.toLowerCase());
    }
    public int getOnlineCount() { return connections.size(); }
}
