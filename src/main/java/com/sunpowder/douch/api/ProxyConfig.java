package com.sunpowder.douch.api;

public interface ProxyConfig {
    String getString(String path);
    int getInt(String path);
    boolean getBoolean(String path);
}
