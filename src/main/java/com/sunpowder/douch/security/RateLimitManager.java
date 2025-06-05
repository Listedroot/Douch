package com.sunpowder.douch.security;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RateLimitManager {
    private final Map<String, Long> lastAction = new ConcurrentHashMap<>();
    private final long minIntervalMillis;

    public RateLimitManager(long minIntervalMillis) {
        this.minIntervalMillis = minIntervalMillis;
    }
    public boolean tryAcquire(String key) {
        long now = System.currentTimeMillis();
        return lastAction.merge(key, now, (prev, curr) -> (curr - prev < minIntervalMillis) ? prev : curr) == now;
    }
}
