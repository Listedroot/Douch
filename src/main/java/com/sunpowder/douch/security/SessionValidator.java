package com.sunpowder.douch.security;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class SessionValidator {
    private final Map<UUID, String> sessionTokens = new ConcurrentHashMap<>();
    public void setSessionToken(UUID uuid, String token) {
        sessionTokens.put(uuid, token);
    }
    public boolean validateSession(UUID uuid, String token) {
        return token.equals(sessionTokens.get(uuid));
    }
    public void invalidateSession(UUID uuid) {
        sessionTokens.remove(uuid);
    }
}
