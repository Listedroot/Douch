package com.sunpowder.douch.api;

import java.util.List;

public interface PluginManager {
    void registerPlugin(Plugin plugin);
    void unregisterPlugin(Plugin plugin);
    List<Plugin> getPlugins();
    Plugin getPlugin(String name);
}
