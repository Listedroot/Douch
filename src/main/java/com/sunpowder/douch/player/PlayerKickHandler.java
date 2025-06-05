package com.sunpowder.douch.player;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PlayerKickHandler {
    private static final Logger logger = LoggerFactory.getLogger(PlayerKickHandler.class);
    private final PlayerManager playerManager;
    public PlayerKickHandler(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }
    public void kick(String username, String reason) {
        PlayerSession session = playerManager.getSession(username);
        if (session != null && session.isOnline()) {
            session.disconnect(reason);
            logger.info("Player {} was kicked for reason: {}", username, reason);
        } else {
            logger.warn("Attempted to kick player {} who is not online.", username);
        }
    }
}
