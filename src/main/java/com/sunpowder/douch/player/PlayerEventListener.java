package com.sunpowder.douch.player;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class PlayerEventListener {
    private final List<PlayerEventHandler> handlers = new CopyOnWriteArrayList<>();
    public void register(PlayerEventHandler handler) {
        handlers.add(handler);
    }
    public void unregister(PlayerEventHandler handler) {
        handlers.remove(handler);
    }
    public void fire(PlayerEvent event) {
        for (PlayerEventHandler handler : handlers) {
            handler.handle(event);
        }
    }
    public interface PlayerEventHandler {
        void handle(PlayerEvent event);
    }
    public static class PlayerEvent {
        private final String type;
        private final String username;
        public PlayerEvent(String type, String username) {
            this.type = type;
            this.username = username;
        }
        public String getType() { return type; }
        public String getUsername() { return username; }
    }
}
