package com.sunpowder.douch.chat;

import java.util.HashMap;
import java.util.Map;

public class EmoteManager {
    private final Map<String, String> emotes = new HashMap<>();

    public void addEmote(String code, String unicode) {
        emotes.put(code, unicode);
    }

    public String replaceEmotes(String message) {
        for (Map.Entry<String, String> entry : emotes.entrySet()) {
            message = message.replace(entry.getKey(), entry.getValue());
        }
        return message;
    }
}
