package com.sunpowder.douch.firewall;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RateLimiter {
    private final Map<String, Long> lastRequest = new ConcurrentHashMap<>();
    private final long minIntervalMillis;

    public RateLimiter(long minIntervalMillis) {
        this.minIntervalMillis = minIntervalMillis;
    }

    public boolean allow(String ip) {
        long now = System.currentTimeMillis();
        Long last = lastRequest.get(ip);
        if (last == null || now - last > minIntervalMillis) {
            lastRequest.put(ip, now);
            return true;
        }
        return false;
    }
}
