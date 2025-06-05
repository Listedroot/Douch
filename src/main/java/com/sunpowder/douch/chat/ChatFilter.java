package com.sunpowder.douch.chat;

import java.util.HashSet;
import java.util.Set;

public class ChatFilter {
    private final Set<String> bannedWords = new HashSet<>();

    public void addBannedWord(String word) {
        bannedWords.add(word.toLowerCase());
    }

    public boolean isAllowed(String message) {
        String lower = message.toLowerCase();
        for (String banned : bannedWords) {
            if (lower.contains(banned)) {
                return false;
            }
        }
        return true;
    }
}
