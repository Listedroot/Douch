package com.sunpowder.douch.metrics;

import java.util.Map;

public class MetricExporter {
    public String export(Map<String, Object> metrics) {
        StringBuilder sb = new StringBuilder();
        metrics.forEach((k, v) -> sb.append(k).append(": ").append(v.toString()).append("\n"));
        return sb.toString();
    }
}
