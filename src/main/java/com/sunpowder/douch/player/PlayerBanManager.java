package com.sunpowder.douch.player;

import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class PlayerBanManager {
    private final Set<String> banned = ConcurrentHashMap.newKeySet();
    private final File banFile;

    public PlayerBanManager(File banFile) {
        this.banFile = banFile;
        load();
    }
    public void ban(String username) {
        banned.add(username.toLowerCase());
        save();
    }
    public void unban(String username) {
        banned.remove(username.toLowerCase());
        save();
    }
    public boolean isBanned(String username) {
        return banned.contains(username.toLowerCase());
    }
    private void load() {
        if (!banFile.exists()) return;
        try (BufferedReader reader = new BufferedReader(new FileReader(banFile))) {
            String line;
            while ((line = reader.readLine()) != null) banned.add(line.trim().toLowerCase());
        } catch (IOException ignored) {}
    }
    private void save() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(banFile))) {
            for (String name : banned) writer.println(name);
        } catch (IOException ignored) {}
    }
}
