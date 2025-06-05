package com.sunpowder.douch.chat;

import java.util.HashSet;
import java.util.Set;

public class ChatMuteManager {
    private final Set<String> mutedUsers = new HashSet<>();

    public void mute(String username) {
        mutedUsers.add(username.toLowerCase());
    }

    public void unmute(String username) {
        mutedUsers.remove(username.toLowerCase());
    }

    public boolean isMuted(String username) {
        return mutedUsers.contains(username.toLowerCase());
    }
}
