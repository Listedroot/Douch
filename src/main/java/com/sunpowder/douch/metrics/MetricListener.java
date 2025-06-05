package com.sunpowder.douch.metrics;

public interface MetricListener {
    void onMetricUpdate(String metricName, Object value);
}
