package com.sunpowder.douch.core;

import java.util.Timer;
import java.util.TimerTask;

public class ProxyScheduler {
    private final Timer timer = new Timer(true);
    public void schedule(Runnable task, long delay, long period) {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override public void run() { task.run(); }
        }, delay, period);
    }
    public void stop() { timer.cancel(); }
}
