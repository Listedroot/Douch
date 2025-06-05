package com.sunpowder.douch.chat;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ChatChannelManager {
    private final Map<String, Set<String>> channels = new HashMap<>();

    public void joinChannel(String username, String channel) {
        channels.computeIfAbsent(channel, k -> new HashSet<>()).add(username);
    }

    public void leaveChannel(String username, String channel) {
        Set<String> users = channels.get(channel);
        if (users != null) users.remove(username);
    }

    public Set<String> getUsers(String channel) {
        return channels.getOrDefault(channel, new HashSet<>());
    }
}
