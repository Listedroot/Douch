package com.sunpowder.douch.metrics;

import java.util.concurrent.atomic.AtomicLong;

public class PacketMetrics {
    private final AtomicLong packetsIn = new AtomicLong();
    private final AtomicLong packetsOut = new AtomicLong();

    public void incrementPacketsIn() {
        packetsIn.incrementAndGet();
    }

    public void incrementPacketsOut() {
        packetsOut.incrementAndGet();
    }

    public long getPacketsIn() {
        return packetsIn.get();
    }

    public long getPacketsOut() {
        return packetsOut.get();
    }
}
