package com.sunpowder.douch.chat;

public class ChatColorizer {
    public String colorize(String message, String colorCode) {
        // Example: Minecraft color codes or ANSI
        return colorCode + message + "\u001B[0m";
    }
}
