package com.sunpowder.douch.player;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class PlayerManager {
    private final Map<String, PlayerSession> sessions = new ConcurrentHashMap<>();
    private final Map<UUID, PlayerSession> uuidSessions = new ConcurrentHashMap<>();

    public void addSession(PlayerSession session) {
        sessions.put(session.getUsername().toLowerCase(), session);
        uuidSessions.put(session.getUuid(), session);
    }
    public void removeSession(String username) {
        PlayerSession session = sessions.remove(username.toLowerCase());
        if (session != null) uuidSessions.remove(session.getUuid());
    }
    public PlayerSession getSession(String username) {
        return sessions.get(username.toLowerCase());
    }
    public PlayerSession getSession(UUID uuid) {
        return uuidSessions.get(uuid);
    }
    public int getOnlineCount() {
        return sessions.size();
    }
}
