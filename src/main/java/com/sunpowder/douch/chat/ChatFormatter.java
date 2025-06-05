package com.sunpowder.douch.chat;

public class ChatFormatter {
    public String format(String username, String message) {
        return "<" + username + "> " + message;
    }
}
