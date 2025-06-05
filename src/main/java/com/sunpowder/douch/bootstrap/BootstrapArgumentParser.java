package com.sunpowder.douch.bootstrap;

import java.util.HashMap;
import java.util.Map;

public class BootstrapArgumentParser {
    public Map<String, String> parse(String[] args) {
        Map<String, String> map = new HashMap<>();
        for (String arg : args) {
            if (arg.startsWith("--")) {
                String[] split = arg.substring(2).split("=", 2);
                if (split.length == 2) map.put(split[0], split[1]);
                else map.put(split[0], "true");
            }
        }
        return map;
    }
}
