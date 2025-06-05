package com.sunpowder.douch.backend;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BackendConnectionPool {
    private final Map<String, BackendConnection> pool = new ConcurrentHashMap<>();
    public void addConnection(String username, BackendConnection connection) {
        pool.put(username, connection);
    }
    public BackendConnection getConnection(String username) {
        return pool.get(username);
    }
    public void removeConnection(String username) {
        pool.remove(username);
    }
}
