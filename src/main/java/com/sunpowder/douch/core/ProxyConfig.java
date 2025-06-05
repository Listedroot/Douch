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
# Network Settings
# proxy.port: The port the proxy listens on
# proxy.host: The IP address to bind (0.0.0.0 for all interfaces)
# proxy.online-mode: Verify players with Mojang's session server (true/false)
# proxy.compression-threshold: Compression threshold in bytes (-1 to disable, 0 to compress everything)
# proxy.compression-level: Compression level (1-9, higher means better compression but more CPU usage)
# proxy.netty-threads: Number of Netty worker threads (0 = auto-detect based on CPU cores)
#
# Security Settings
# firewall.enabled: Enable or disable the firewall (true/false)
# max.connections: Maximum simultaneous connections per IP
# rate-limit: Maximum packets per second per connection
#
# Backend Settings
# backend.servers: Comma-separated list of backend Paper server addresses (host:port)
# backend.connect-timeout: Connection timeout in milliseconds
# backend.read-timeout: Read timeout in milliseconds
#
# Debug Settings
# debug: Enable debug logging (true/false)
#
# Default values
proxy.port=25565
proxy.host=0.0.0.0
proxy.online-mode=true
proxy.compression-threshold=256
proxy.compression-level=6
proxy.netty-threads=0

firewall.enabled=true
max.connections=10
rate-limit=1000

backend.servers=127.0.0.1:25566
backend.connect-timeout=5000
backend.read-timeout=30000

debug=false
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

    // Network settings
    public int getPort() {
        return getInt("proxy.port", 25565);
    }

    public String getHost() {
        return get("proxy.host", "0.0.0.0");
    }

    public boolean isOnlineMode() {
        return getBoolean("proxy.online-mode", true);
    }

    public int getCompressionThreshold() {
        return getInt("proxy.compression-threshold", 256);
    }

    public int getCompressionLevel() {
        return getInt("proxy.compression-level", 6);
    }

    public int getNettyThreads() {
        return getInt("proxy.netty-threads", 0);
    }

    // Security settings
    public boolean isFirewallEnabled() {
        return getBoolean("firewall.enabled", true);
    }

    public int getMaxConnections() {
        return getInt("max.connections", 10);
    }

    public int getRateLimit() {
        return getInt("rate-limit", 1000);
    }

    // Backend settings
    public String[] getBackendServers() {
        return get("backend.servers", "127.0.0.1:25566").split(",");
    }

    public int getBackendConnectTimeout() {
        return getInt("backend.connect-timeout", 5000);
    }

    public int getBackendReadTimeout() {
        return getInt("backend.read-timeout", 30000);
    }

    // Debug settings
    public boolean isDebug() {
        return getBoolean("debug", false);
    }

    // Helper method with default value
    private String get(String key, String def) {
        return properties.getProperty(key, def);
    }
}
