package com.sunpowder.douch.chat;

import java.util.logging.Logger;

public class ChatLogger {
    private final Logger logger = Logger.getLogger("DouchChat");

    public void logMessage(String message) {
        logger.info(message);
    }
}
