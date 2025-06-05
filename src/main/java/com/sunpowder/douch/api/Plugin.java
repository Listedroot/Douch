package com.sunpowder.douch.api;

public interface Plugin {
    void onEnable();
    void onDisable();
    String getName();
    String getVersion();
}
