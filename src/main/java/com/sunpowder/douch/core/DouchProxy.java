package com.sunpowder.douch.core;

import com.sunpowder.douch.network.NetworkManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DouchProxy {
    private static final Logger logger = LoggerFactory.getLogger(DouchProxy.class);
    private final NetworkManager networkManager;

    public DouchProxy() {
        this.networkManager = new NetworkManager();
    }

    public void start() throws Exception {
        logger.info("Initializing network manager...");
        networkManager.start();
        logger.info("Douch Proxy is now running.");
    }
}
