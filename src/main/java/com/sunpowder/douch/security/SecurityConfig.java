package com.sunpowder.douch.security;

import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.yaml.YamlConfigurationLoader;
import java.io.File;

public class SecurityConfig {
    private final ConfigurationNode root;
    public SecurityConfig(File file) throws Exception {
        this.root = YamlConfigurationLoader.builder().path(file.toPath()).build().load();
    }
    public String getString(String path) { return root.node((Object[]) path.split("\\.")).getString(); }
    public int getInt(String path) { return root.node((Object[]) path.split("\\.")).getInt(); }
    public boolean getBoolean(String path) { return root.node((Object[]) path.split("\\.")).getBoolean(); }
}
