package com.sunpowder.douch.player;

import java.util.UUID;
import java.util.Map;

public class PlayerProfile {
    private final String username;
    private final UUID uuid;
    private final Map<String, String> properties;

    public PlayerProfile(String username, UUID uuid, Map<String, String> properties) {
        this.username = username;
        this.uuid = uuid;
        this.properties = properties;
    }

    public String getUsername() { return username; }
    public UUID getUuid() { return uuid; }
    public Map<String, String> getProperties() { return properties; }
    public String getProperty(String key) { return properties.get(key); }
}
