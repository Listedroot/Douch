package com.sunpowder.douch.metrics;

public class TPSMetrics {
    private volatile double tps = 20.0;

    public void updateTPS(double newTps) {
        this.tps = newTps;
    }

    public double getTPS() {
        return tps;
    }
}
