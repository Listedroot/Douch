package com.sunpowder.douch.chat;

import java.util.LinkedList;
import java.util.List;

public class ChatHistoryManager {
    private final LinkedList<String> history = new LinkedList<>();
    private final int maxSize;

    public ChatHistoryManager(int maxSize) {
        this.maxSize = maxSize;
    }

    public void add(String message) {
        if (history.size() >= maxSize) {
            history.removeFirst();
        }
        history.addLast(message);
    }

    public List<String> getHistory() {
        return new LinkedList<>(history);
    }
}
