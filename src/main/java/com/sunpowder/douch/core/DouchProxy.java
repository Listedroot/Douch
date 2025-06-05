package com.sunpowder.douch.core;

import com.sunpowder.douch.network.NetworkManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicBoolean;

public class DouchProxy {
    private static final Logger logger = LoggerFactory.getLogger(DouchProxy.class);
    
    private final NetworkManager networkManager;
    private final AtomicBoolean running = new AtomicBoolean(false);
    private final boolean onlineMode;
    private final int port;
    
    public DouchProxy() {
        this(true, 25565);
    }
    
    public DouchProxy(boolean onlineMode, int port) {
        this.onlineMode = onlineMode;
        this.port = port;
        this.networkManager = new NetworkManager(this, onlineMode, port);
    }
    
    public void start() {
        if (running.compareAndSet(false, true)) {
            logger.info("Starting Douch Proxy (onlineMode={}, port={})...", onlineMode, port);
            
            // Register shutdown hook
            Runtime.getRuntime().addShutdownHook(new Thread(this::stop, "Shutdown Thread"));
            
            try {
                networkManager.start();
                logger.info("Douch Proxy is now running on port {} (online mode: {})", port, onlineMode);
            } catch (Exception e) {
                logger.error("Failed to start network manager", e);
                System.exit(1);
            }
        }
    }
    
    public void stop() {
        if (running.compareAndSet(true, false)) {
            logger.info("Stopping Douch Proxy...");
            networkManager.stop();
            logger.info("Douch Proxy has been stopped");
        }
    }
    
    public boolean isOnlineMode() {
        return onlineMode;
    }
    
    public int getPort() {
        return port;
    }
    
    public static void main(String[] args) {
        // Default configuration
        boolean onlineMode = true;
        int port = 25565;
        
        // Parse command line arguments
        for (String arg : args) {
            if (arg.equalsIgnoreCase("--offline")) {
                onlineMode = false;
            } else if (arg.startsWith("--port=")) {
                try {
                    port = Integer.parseInt(arg.substring(7));
                } catch (NumberFormatException e) {
                    System.err.println("Invalid port number: " + arg);
                    System.exit(1);
                }
            } else if (arg.equals("--help")) {
                System.out.println("Usage: java -jar douch.jar [options]");
                System.out.println("Options:");
                System.out.println("  --offline    Run in offline mode (no authentication)");
                System.out.println("  --port=<port>  Set the port to listen on (default: 25565)");
                System.out.println("  --help       Show this help message");
                System.exit(0);
            }
        }
        
        // Start the proxy
        try {
            new DouchProxy(onlineMode, port).start();
        } catch (Exception e) {
            logger.error("Failed to start Douch Proxy", e);
            System.exit(1);
        }
    }
}
