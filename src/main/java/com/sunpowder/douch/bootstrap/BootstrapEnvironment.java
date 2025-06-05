package com.sunpowder.douch.bootstrap;

import java.util.Map;

public class BootstrapEnvironment {
    public String getEnv(String key) { return System.getenv(key); }
    public Map<String, String> getAll() { return System.getenv(); }
}
