package com.sunpowder.douch.metrics;

import java.util.concurrent.atomic.AtomicInteger;

public class ConnectionMetrics {
    private final AtomicInteger activeConnections = new AtomicInteger();
    private final AtomicInteger totalConnections = new AtomicInteger();

    public void incrementActive() {
        activeConnections.incrementAndGet();
        totalConnections.incrementAndGet();
    }

    public void decrementActive() {
        activeConnections.decrementAndGet();
    }

    public int getActiveConnections() {
        return activeConnections.get();
    }

    public int getTotalConnections() {
        return totalConnections.get();
    }
}
