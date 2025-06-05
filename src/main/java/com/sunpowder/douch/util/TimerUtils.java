package com.sunpowder.douch.util;

import java.util.Timer;
import java.util.TimerTask;

public class TimerUtils {
    private static final Timer timer = new Timer(true);
    public static void schedule(Runnable task, long delayMillis) {
        timer.schedule(new TimerTask() {
            @Override public void run() { task.run(); }
        }, delayMillis);
    }
    public static void scheduleRepeating(Runnable task, long delayMillis, long periodMillis) {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override public void run() { task.run(); }
        }, delayMillis, periodMillis);
    }
}
