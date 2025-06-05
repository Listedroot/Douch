package com.sunpowder.douch.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProxyLogger {
    private static final Logger logger = LoggerFactory.getLogger(ProxyLogger.class);
    public void info(String msg) { logger.info(msg); }
    public void warn(String msg) { logger.warn(msg); }
    public void error(String msg) { logger.error(msg); }
}
