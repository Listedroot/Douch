package com.sunpowder.douch.api;

public interface Scheduler {
    Task schedule(Runnable runnable, long delayMillis);
    Task scheduleRepeating(Runnable runnable, long delayMillis, long periodMillis);
}
