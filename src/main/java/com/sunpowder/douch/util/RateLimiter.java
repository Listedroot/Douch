package com.sunpowder.douch.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RateLimiter {
    private final long intervalMillis;
    private final Map<String, Long> lastTime = new ConcurrentHashMap<>();
    public RateLimiter(long intervalMillis) {
        this.intervalMillis = intervalMillis;
    }
    public boolean tryAcquire(String key) {
        long now = System.currentTimeMillis();
        return lastTime.compute(key, (k, last) -> {
            if (last == null || now - last >= intervalMillis) return now;
            return last;
        }) == now;
    }
}
