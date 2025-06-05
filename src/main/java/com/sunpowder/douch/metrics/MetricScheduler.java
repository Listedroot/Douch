package com.sunpowder.douch.metrics;

import java.util.Timer;
import java.util.TimerTask;

public class MetricScheduler {
    private final Timer timer = new Timer();

    public void schedule(Runnable task, long delay, long period) {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                task.run();
            }
        }, delay, period);
    }

    public void stop() {
        timer.cancel();
    }
}
