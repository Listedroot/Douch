package com.sunpowder.douch.chat;

import java.util.HashMap;
import java.util.Map;

public class PrivateMessageManager {
    private final Map<String, String> lastPrivateMessage = new HashMap<>();

    public void sendPrivateMessage(String from, String to, String message) {
        // Simulate sending private message
        lastPrivateMessage.put(to, "From " + from + ": " + message);
    }

    public String getLastPrivateMessage(String user) {
        return lastPrivateMessage.get(user);
    }
}
