package com.sunpowder.douch.player;

import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

public class PlayerTabList {
    private final Set<String> tabPlayers = new ConcurrentSkipListSet<>();
    public void addPlayer(String username) {
        tabPlayers.add(username);
    }
    public void removePlayer(String username) {
        tabPlayers.remove(username);
    }
    public Set<String> getTabPlayers() {
        return tabPlayers;
    }
    public void clear() {
        tabPlayers.clear();
    }
}
