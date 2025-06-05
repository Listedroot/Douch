package com.sunpowder.douch.player;

import java.io.*;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class PlayerMuteManager {
    private final Set<String> muted = ConcurrentHashMap.newKeySet();
    private final File muteFile;

    public PlayerMuteManager(File muteFile) {
        this.muteFile = muteFile;
        load();
    }
    public void mute(String username) {
        muted.add(username.toLowerCase());
        save();
    }
    public void unmute(String username) {
        muted.remove(username.toLowerCase());
        save();
    }
    public boolean isMuted(String username) {
        return muted.contains(username.toLowerCase());
    }
    private void load() {
        if (!muteFile.exists()) return;
        try (BufferedReader reader = new BufferedReader(new FileReader(muteFile))) {
            String line;
            while ((line = reader.readLine()) != null) muted.add(line.trim().toLowerCase());
        } catch (IOException ignored) {}
    }
    private void save() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(muteFile))) {
            for (String name : muted) writer.println(name);
        } catch (IOException ignored) {}
    }
}
