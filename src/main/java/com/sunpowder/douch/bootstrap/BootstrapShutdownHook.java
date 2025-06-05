package com.sunpowder.douch.bootstrap;

public class BootstrapShutdownHook extends Thread {
    private final Runnable onShutdown;
    public BootstrapShutdownHook(Runnable onShutdown) {
        this.onShutdown = onShutdown;
    }
    @Override
    public void run() {
        onShutdown.run();
    }
}
