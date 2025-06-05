package com.sunpowder.douch.metrics;

import java.util.function.Consumer;

public class MetricAlertManager {
    private double latencyThreshold = 500.0;
    private Consumer<String> alertHandler;

    public void setLatencyThreshold(double threshold) {
        this.latencyThreshold = threshold;
    }

    public void setAlertHandler(Consumer<String> handler) {
        this.alertHandler = handler;
    }

    public void checkLatency(double latency) {
        if (latency > latencyThreshold && alertHandler != null) {
            alertHandler.accept("High latency detected: " + latency + "ms");
        }
    }
}
