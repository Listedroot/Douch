package com.sunpowder.douch.backend;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BackendHealthMonitor {
    private final Map<String, Boolean> health = new ConcurrentHashMap<>();
    public void setHealthy(String server, boolean healthy) {
        health.put(server, healthy);
    }
    public boolean isHealthy(String server) {
        return health.getOrDefault(server, false);
    }
}
