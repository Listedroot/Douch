package com.sunpowder.douch.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SecurityLogger {
    private static final Logger logger = LoggerFactory.getLogger(SecurityLogger.class);
    public void log(String message) {
        logger.info(message);
    }
    public void warn(String message) {
        logger.warn(message);
    }
    public void error(String message) {
        logger.error(message);
    }
}
