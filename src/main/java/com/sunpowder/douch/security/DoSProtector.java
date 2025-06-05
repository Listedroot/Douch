package com.sunpowder.douch.security;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DoSProtector {
    private final Map<String, Long> lastConnection = new ConcurrentHashMap<>();
    private final long minIntervalMillis;

    public DoSProtector(long minIntervalMillis) {
        this.minIntervalMillis = minIntervalMillis;
    }
    public boolean allow(String ip) {
        long now = System.currentTimeMillis();
        return lastConnection.merge(ip, now, (prev, curr) -> (curr - prev < minIntervalMillis) ? prev : curr) == now;
    }
}
