package com.sunpowder.douch.chat;

import java.util.HashMap;
import java.util.Map;

public class ChatRateLimiter {
    private final Map<String, Long> lastMessageTime = new HashMap<>();
    private final long cooldownMillis;

    public ChatRateLimiter(long cooldownMillis) {
        this.cooldownMillis = cooldownMillis;
    }

    public boolean canSend(String username) {
        long now = System.currentTimeMillis();
        Long last = lastMessageTime.get(username);
        if (last == null || now - last > cooldownMillis) {
            lastMessageTime.put(username, now);
            return true;
        }
        return false;
    }
}
