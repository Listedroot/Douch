package com.sunpowder.douch.bootstrap;

public class BootstrapMetrics {
    private long startTime;
    public void start() { startTime = System.currentTimeMillis(); }
    public long getUptime() { return System.currentTimeMillis() - startTime; }
}
