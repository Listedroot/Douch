package com.sunpowder.douch.core;

import com.sunpowder.douch.network.NetworkManager;
import com.sunpowder.douch.backend.BackendServerRegistry;
import com.sunpowder.douch.player.PlayerManager;

public class ProxyServer {
    private final NetworkManager networkManager;
    private final BackendServerRegistry backendRegistry;
    private final PlayerManager playerManager;

    public ProxyServer(NetworkManager networkManager, BackendServerRegistry backendRegistry, PlayerManager playerManager) {
        this.networkManager = networkManager;
        this.backendRegistry = backendRegistry;
        this.playerManager = playerManager;
    }

    public void start() {
        try {
            networkManager.start();
        } catch (Exception e) {
            throw new RuntimeException("Failed to start NetworkManager", e);
        }
        backendRegistry.loadServers();
        System.out.println("Proxy server started.");
    }

    public void stop() {
        networkManager.stop();
        System.out.println("Proxy server stopped.");
    }
}
