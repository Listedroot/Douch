package com.sunpowder.douch.bootstrap;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class BootstrapPluginLoader {
    public List<File> loadPlugins(File pluginDir) {
        List<File> plugins = new ArrayList<>();
        if (pluginDir.exists() && pluginDir.isDirectory()) {
            for (File file : pluginDir.listFiles()) {
                if (file.isFile() && file.getName().endsWith(".jar")) {
                    plugins.add(file);
                }
            }
        }
        return plugins;
    }
}
