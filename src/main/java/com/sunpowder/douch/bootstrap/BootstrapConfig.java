package com.sunpowder.douch.bootstrap;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;

public class BootstrapConfig {
    private final Properties properties = new Properties();
    public BootstrapConfig(File file) throws Exception {
        try (FileReader reader = new FileReader(file)) {
            properties.load(reader);
        }
    }
    public String get(String key) { return properties.getProperty(key); }
    public int getInt(String key, int def) {
        try { return Integer.parseInt(properties.getProperty(key)); }
        catch (Exception e) { return def; }
    }
    public boolean getBoolean(String key, boolean def) {
        String val = properties.getProperty(key);
        if (val == null) return def;
        return Boolean.parseBoolean(val);
    }
}
