package com.sunpowder.douch.util;

import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.yaml.YamlConfigurationLoader;
import java.io.File;

public class ConfigUtils {
    public static ConfigurationNode loadYaml(File file) throws Exception {
        return YamlConfigurationLoader.builder().path(file.toPath()).build().load();
    }
}
