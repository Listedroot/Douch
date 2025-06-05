package com.sunpowder.douch.backend;

import com.sunpowder.douch.player.PlayerManager;
import com.sunpowder.douch.player.PlayerSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BackendKickHandler {
    private static final Logger logger = LoggerFactory.getLogger(BackendKickHandler.class);
    private final PlayerManager playerManager;

    public BackendKickHandler(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    public void handleKick(String username, String reason) {
        PlayerSession session = playerManager.getSession(username);
        if (session != null) {
            session.disconnect("Kicked from backend: " + reason);
            logger.info("Player {} kicked from backend for reason: {}", username, reason);
        } else {
            logger.warn("Attempted to kick player {} who is not online.", username);
        }
    }
}
