package com.sunpowder.douch.backend;

public interface BackendStatusListener {
    void onStatusChange(String server, boolean online);
}
