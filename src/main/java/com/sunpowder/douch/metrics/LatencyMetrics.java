package com.sunpowder.douch.metrics;

import java.util.concurrent.atomic.AtomicLong;

public class LatencyMetrics {
    private final AtomicLong totalLatency = new AtomicLong();
    private final AtomicLong count = new AtomicLong();

    public void addSample(long latencyMs) {
        totalLatency.addAndGet(latencyMs);
        count.incrementAndGet();
    }

    public double getAverageLatency() {
        long c = count.get();
        return c == 0 ? 0 : (double) totalLatency.get() / c;
    }
}
