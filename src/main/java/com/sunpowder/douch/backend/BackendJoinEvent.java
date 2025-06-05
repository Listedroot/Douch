package com.sunpowder.douch.backend;

public class BackendJoinEvent implements BackendEvent {
    private final String serverName;
    private final String username;
    private final long timestamp;
    public BackendJoinEvent(String serverName, String username) {
        this.serverName = serverName;
        this.username = username;
        this.timestamp = System.currentTimeMillis();
    }
    public String getUsername() { return username; }
    @Override public String getServerName() { return serverName; }
    @Override public long getTimestamp() { return timestamp; }
}
