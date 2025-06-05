package com.sunpowder.douch.api;

public interface CommandSender {
    void sendMessage(String message);
    boolean hasPermission(String permission);
    String getName();
}
