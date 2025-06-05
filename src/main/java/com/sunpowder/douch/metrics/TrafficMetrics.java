package com.sunpowder.douch.metrics;

import java.util.concurrent.atomic.AtomicLong;

public class TrafficMetrics {
    private final AtomicLong bytesRead = new AtomicLong();
    private final AtomicLong bytesWritten = new AtomicLong();

    public void addBytesRead(long bytes) {
        bytesRead.addAndGet(bytes);
    }

    public void addBytesWritten(long bytes) {
        bytesWritten.addAndGet(bytes);
    }

    public long getBytesRead() {
        return bytesRead.get();
    }

    public long getBytesWritten() {
        return bytesWritten.get();
    }
}
