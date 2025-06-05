package com.sunpowder.douch.core;

public class ProxyShutdownHook extends Thread {
    private final Runnable onShutdown;
    public ProxyShutdownHook(Runnable onShutdown) {
        this.onShutdown = onShutdown;
    }
    @Override
    public void run() {
        onShutdown.run();
    }
}
