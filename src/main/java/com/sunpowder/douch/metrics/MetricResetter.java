package com.sunpowder.douch.metrics;

import java.util.Map;

public class MetricResetter {
    public void reset(Map<String, Object> metrics) {
        metrics.forEach((k, v) -> {
            if (v instanceof java.util.concurrent.atomic.AtomicInteger) {
                ((java.util.concurrent.atomic.AtomicInteger) v).set(0);
            } else if (v instanceof java.util.concurrent.atomic.AtomicLong) {
                ((java.util.concurrent.atomic.AtomicLong) v).set(0);
            }
        });
    }
}
