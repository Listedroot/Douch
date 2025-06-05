package com.sunpowder.douch.metrics;

import java.util.Map;

public class MetricCollector {
    public void collect(Map<String, Object> metrics) {
        // Implement real collection logic here (e.g., send to DB, log, etc.)
        // For now, just print
        metrics.forEach((k, v) -> System.out.println(k + ": " + v));
    }
}
