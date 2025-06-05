package com.sunpowder.douch.backend;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BackendReconnectHandler {
    private final Map<String, String> lastServer = new ConcurrentHashMap<>();
    public void setLastServer(String username, String server) {
        lastServer.put(username, server);
    }
    public String getLastServer(String username) {
        return lastServer.get(username);
    }
}
