package com.sunpowder.douch.security;

import java.util.Timer;
import java.util.TimerTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SecurityAudit {
    private static final Logger logger = LoggerFactory.getLogger(SecurityAudit.class);
    private final Timer timer = new Timer(true);
    public void scheduleAudit(Runnable check, long periodMillis) {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override public void run() { check.run(); logger.info("Security audit performed."); }
        }, periodMillis, periodMillis);
    }
}
