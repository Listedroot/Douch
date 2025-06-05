package com.sunpowder.douch.api;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class PluginLoader {
    public Plugin loadPlugin(File file) throws Exception {
        URL url = file.toURI().toURL();
        try (URLClassLoader loader = new URLClassLoader(new URL[]{url}, getClass().getClassLoader())) {
            // Find main class from plugin.yml or similar
            String mainClass = "com.example.PluginMain"; // For demonstration
            Class<?> clazz = loader.loadClass(mainClass);
            return (Plugin) clazz.getDeclaredConstructor().newInstance();
        }
    }
}
