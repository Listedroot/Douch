package com.sunpowder.douch.util;

import java.util.UUID;

public class UUIDUtils {
    public static UUID fromString(String uuid) {
        return UUID.fromString(uuid);
    }
    public static String toString(UUID uuid) {
        return uuid.toString();
    }
    public static UUID randomUUID() {
        return UUID.randomUUID();
    }
}
