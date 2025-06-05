package com.sunpowder.douch.chat;

import java.util.Set;

public class ChatWordCensor {
    private final Set<String> censoredWords;

    public ChatWordCensor(Set<String> censoredWords) {
        this.censoredWords = censoredWords;
    }

    public String censor(String message) {
        String censored = message;
        for (String word : censoredWords) {
            censored = censored.replaceAll("(?i)" + word, repeatAsterisks(word.length()));
        }
        return censored;
    }

    private String repeatAsterisks(int count) {
        return "*".repeat(Math.max(0, count));
    }
}
