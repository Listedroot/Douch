package com.sunpowder.douch.network;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MCPacketRegistry {
    private final Map<Integer, Class<?>> registry = new ConcurrentHashMap<>();
    public void register(int id, Class<?> clazz) {
        registry.put(id, clazz);
    }
    public Class<?> getPacketClass(int id) {
        return registry.get(id);
    }
}
