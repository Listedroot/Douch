package com.sunpowder.douch.bootstrap;

public class BootstrapExceptionHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.err.println("Uncaught exception in thread " + t.getName() + ": " + e.getMessage());
        e.printStackTrace();
    }
}
