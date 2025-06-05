package com.sunpowder.douch.player;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class PlayerLocaleManager {
    private final Map<UUID, String> locales = new ConcurrentHashMap<>();
    public void setLocale(UUID uuid, String locale) {
        locales.put(uuid, locale);
    }
    public String getLocale(UUID uuid) {
        return locales.getOrDefault(uuid, "en_us");
    }
    public void removeLocale(UUID uuid) {
        locales.remove(uuid);
    }
}
