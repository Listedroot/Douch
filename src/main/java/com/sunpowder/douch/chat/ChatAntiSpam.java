package com.sunpowder.douch.chat;

import java.util.HashMap;
import java.util.Map;

public class ChatAntiSpam {
    private final Map<String, Integer> messageCounts = new HashMap<>();
    private final long timeWindowMillis;
    private final int maxMessages;
    private final Map<String, Long> firstMessageTime = new HashMap<>();

    public ChatAntiSpam(long timeWindowMillis, int maxMessages) {
        this.timeWindowMillis = timeWindowMillis;
        this.maxMessages = maxMessages;
    }

    public boolean isSpamming(String username) {
        long now = System.currentTimeMillis();
        firstMessageTime.putIfAbsent(username, now);
        if (now - firstMessageTime.get(username) > timeWindowMillis) {
            firstMessageTime.put(username, now);
            messageCounts.put(username, 1);
            return false;
        }
        int count = messageCounts.getOrDefault(username, 0) + 1;
        messageCounts.put(username, count);
        return count > maxMessages;
    }
}
