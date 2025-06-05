package com.sunpowder.douch.bootstrap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BootstrapLogger {
    private static final Logger logger = LoggerFactory.getLogger(BootstrapLogger.class);
    public void info(String msg) { logger.info(msg); }
    public void warn(String msg) { logger.warn(msg); }
    public void error(String msg) { logger.error(msg); }
}
