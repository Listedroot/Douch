package com.sunpowder.douch.chat;

import java.util.ArrayList;
import java.util.List;

public class ChatManager {
    private final List<String> chatLog = new ArrayList<>();

    public void addMessage(String username, String message) {
        chatLog.add("[" + username + "]: " + message);
    }

    public List<String> getChatLog() {
        return new ArrayList<>(chatLog);
    }

    public void clearChat() {
        chatLog.clear();
    }
}
