package com.sunpowder.douch.chat;

import java.util.LinkedList;
import java.util.List;

public class ChatReplayManager {
    private final LinkedList<String> replayBuffer = new LinkedList<>();
    private final int maxSize;

    public ChatReplayManager(int maxSize) {
        this.maxSize = maxSize;
    }

    public void addMessage(String message) {
        if (replayBuffer.size() >= maxSize) {
            replayBuffer.removeFirst();
        }
        replayBuffer.addLast(message);
    }

    public List<String> getReplay() {
        return new LinkedList<>(replayBuffer);
    }
}
