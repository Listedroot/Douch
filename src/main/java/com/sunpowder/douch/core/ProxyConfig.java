package com.sunpowder.douch.core;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class ProxyConfig {
    private final Properties properties = new Properties();
    private static final String DEFAULT_CONFIG = """
# Douch Proxy Configuration
#
# proxy.port: The port the proxy listens on
# proxy.host: The IP address to bind (0.0.0.0 for all interfaces)
# firewall.enabled: Enable or disable the firewall (true/false)
# max.connections: Maximum simultaneous connections per IP
# backend.servers: Comma-separated list of backend Paper server addresses (host:port)
#
proxy.port=25565
proxy.host=0.0.0.0
firewall.enabled=true
max.connections=10
backend.servers=127.0.0.1:25566
""";

    public ProxyConfig(File file) throws Exception {
        if (!file.exists()) {
            try (FileWriter writer = new FileWriter(file)) {
                writer.write(DEFAULT_CONFIG);
            }
        }
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
