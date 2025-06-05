package com.sunpowder.douch.bootstrap;

import com.sunpowder.douch.core.DouchProxy;
import com.sunpowder.douch.core.ProxyServer;
import com.sunpowder.douch.network.NetworkManager;
import com.sunpowder.douch.backend.BackendServerRegistry;
import com.sunpowder.douch.player.PlayerManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DouchBootstrap {
    private static final Logger logger = LoggerFactory.getLogger(DouchBootstrap.class);

    public static void main(String[] args) {
        System.out.println("\n========================================");
        System.out.println(" Douch Proxy v1.0.0");
        System.out.println(" High-performance Minecraft Proxy");
        System.out.println(" Developed by MONDERASDOR");
        System.out.println("========================================");
        System.out.println("Starting proxy...");

        NetworkManager networkManager = new NetworkManager();
        BackendServerRegistry backendRegistry = new BackendServerRegistry();
        PlayerManager playerManager = new PlayerManager();
        ProxyServer proxy = new ProxyServer(networkManager, backendRegistry, playerManager);
        try {
            proxy.start();
        } catch (Exception e) {
            logger.error("Failed to start Douch Proxy", e);
            System.exit(1);
        }

        System.out.println("Proxy is now running and managing backend Paper servers.");
        System.out.println("Type 'help' for available commands. Press Ctrl+C to stop.");

        java.util.Scanner scanner = new java.util.Scanner(System.in);
        while (true) {
            System.out.print("douch> ");
            String input = scanner.nextLine();
            if ("stop".equalsIgnoreCase(input.trim())) {
                try {
                    proxy.stop();
                } catch (Exception e) {
                    logger.error("Failed to stop Douch Proxy", e);
                }
                System.out.println("Proxy stopped.");
                break;
            } else if ("help".equalsIgnoreCase(input.trim())) {
                System.out.println("Available commands: help, stop");
            } else {
                System.out.println("Unknown command. Type 'help' for commands.");
            }
        }
    }
}
