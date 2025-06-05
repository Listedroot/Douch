package com.sunpowder.douch.proxyadmin;

import java.util.HashMap;
import java.util.Map;

public class SessionManager {
    private final Map<String, Long> sessions = new HashMap<>();

    public void startSession(String admin) {
        sessions.put(admin, System.currentTimeMillis());
    }

    public void endSession(String admin) {
        sessions.remove(admin);
    }

    public boolean isSessionActive(String admin) {
        return sessions.containsKey(admin);
    }
}
