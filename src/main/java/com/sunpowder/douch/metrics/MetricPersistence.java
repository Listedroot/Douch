package com.sunpowder.douch.metrics;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class MetricPersistence {
    private final String filePath;

    public MetricPersistence(String filePath) {
        this.filePath = filePath;
    }

    public void persist(Map<String, Object> metrics) throws IOException {
        try (FileWriter writer = new FileWriter(filePath, true)) {
            for (Map.Entry<String, Object> entry : metrics.entrySet()) {
                writer.write(entry.getKey() + ": " + entry.getValue().toString() + "\n");
            }
        }
    }
}
