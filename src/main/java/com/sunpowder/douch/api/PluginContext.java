package com.sunpowder.douch.api;

public interface PluginContext {
    ProxyLogger getLogger();
    Scheduler getScheduler();
    ProxyConfig getConfig();
    ProxyServer getProxyServer();
}
