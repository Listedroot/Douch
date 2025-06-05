package com.sunpowder.douch.util;

public class ThreadUtils {
    public static void sleep(long millis) {
        try { Thread.sleep(millis); } catch (InterruptedException ignored) {}
    }
    public static Thread runAsync(Runnable r) {
        Thread t = new Thread(r);
        t.start();
        return t;
    }
}
