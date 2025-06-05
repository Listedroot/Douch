package com.sunpowder.douch.player;

import java.io.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PlayerStatistics {
    private final Map<String, Integer> stats = new ConcurrentHashMap<>();
    private final File statsFile;

    public PlayerStatistics(File statsFile) {
        this.statsFile = statsFile;
        load();
    }
    public void setStat(String username, int value) {
        stats.put(username.toLowerCase(), value);
        save();
    }
    public int getStat(String username) {
        return stats.getOrDefault(username.toLowerCase(), 0);
    }
    private void load() {
        if (!statsFile.exists()) return;
        try (BufferedReader reader = new BufferedReader(new FileReader(statsFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) stats.put(parts[0], Integer.parseInt(parts[1]));
            }
        } catch (IOException ignored) {}
    }
    private void save() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(statsFile))) {
            for (Map.Entry<String, Integer> entry : stats.entrySet())
                writer.println(entry.getKey() + ":" + entry.getValue());
        } catch (IOException ignored) {}
    }
}
