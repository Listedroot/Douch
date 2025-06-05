package com.sunpowder.douch.security;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class BlacklistManager {
    private final Set<String> blacklist = new HashSet<>();
    private final File file;

    public BlacklistManager(File file) {
        this.file = file;
        load();
    }
    public void add(String ip) {
        blacklist.add(ip);
        save();
    }
    public void remove(String ip) {
        blacklist.remove(ip);
        save();
    }
    public boolean isBlacklisted(String ip) {
        return blacklist.contains(ip);
    }
    private void load() {
        if (!file.exists()) return;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) blacklist.add(line.trim());
        } catch (IOException ignored) {}
    }
    private void save() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
            for (String ip : blacklist) writer.println(ip);
        } catch (IOException ignored) {}
    }
}
