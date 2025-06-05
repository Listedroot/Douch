package com.sunpowder.douch.security;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class WhitelistManager {
    private final Set<String> whitelist = new HashSet<>();
    private final File file;

    public WhitelistManager(File file) {
        this.file = file;
        load();
    }
    public void add(String ip) {
        whitelist.add(ip);
        save();
    }
    public void remove(String ip) {
        whitelist.remove(ip);
        save();
    }
    public boolean isWhitelisted(String ip) {
        return whitelist.contains(ip);
    }
    private void load() {
        if (!file.exists()) return;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) whitelist.add(line.trim());
        } catch (IOException ignored) {}
    }
    private void save() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
            for (String ip : whitelist) writer.println(ip);
        } catch (IOException ignored) {}
    }
}
