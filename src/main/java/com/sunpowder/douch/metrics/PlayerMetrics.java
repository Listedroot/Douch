package com.sunpowder.douch.metrics;

import java.util.concurrent.atomic.AtomicInteger;

public class PlayerMetrics {
    private final AtomicInteger onlinePlayers = new AtomicInteger();
    private final AtomicInteger maxPlayers = new AtomicInteger();

    public void setOnlinePlayers(int count) {
        onlinePlayers.set(count);
    }

    public void setMaxPlayers(int count) {
        maxPlayers.set(count);
    }

    public int getOnlinePlayers() {
        return onlinePlayers.get();
    }

    public int getMaxPlayers() {
        return maxPlayers.get();
    }
}
