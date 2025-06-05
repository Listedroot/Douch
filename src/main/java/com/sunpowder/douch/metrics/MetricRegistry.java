package com.sunpowder.douch.metrics;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MetricRegistry {
    private final Map<String, Object> metrics = new HashMap<>();

    public void register(String name, Object metric) {
        metrics.put(name, metric);
    }

    public Object getMetric(String name) {
        return metrics.get(name);
    }

    public Map<String, Object> getAllMetrics() {
        return Collections.unmodifiableMap(metrics);
    }
}
