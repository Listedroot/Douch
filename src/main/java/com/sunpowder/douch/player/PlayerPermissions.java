package com.sunpowder.douch.player;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class PlayerPermissions {
    private final Set<String> permissions = ConcurrentHashMap.newKeySet();

    public void addPermission(String permission) {
        permissions.add(permission.toLowerCase());
    }
    public void removePermission(String permission) {
        permissions.remove(permission.toLowerCase());
    }
    public boolean hasPermission(String permission) {
        return permissions.contains(permission.toLowerCase());
    }
    public Set<String> getAllPermissions() {
        return permissions;
    }
}
