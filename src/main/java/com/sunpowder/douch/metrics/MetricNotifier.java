package com.sunpowder.douch.metrics;

import java.util.ArrayList;
import java.util.List;

public class MetricNotifier {
    private final List<MetricListener> listeners = new ArrayList<>();

    public void addListener(MetricListener listener) {
        listeners.add(listener);
    }

    public void notifyAll(String metricName, Object value) {
        for (MetricListener listener : listeners) {
            listener.onMetricUpdate(metricName, value);
        }
    }
}
