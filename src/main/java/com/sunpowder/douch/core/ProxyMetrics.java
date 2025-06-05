package com.sunpowder.douch.core;

public class ProxyMetrics {
    private long startTime;
    public void start() { startTime = System.currentTimeMillis(); }
    public long getUptime() { return System.currentTimeMillis() - startTime; }
}
