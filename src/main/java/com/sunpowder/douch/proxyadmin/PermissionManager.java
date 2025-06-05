package com.sunpowder.douch.proxyadmin;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

public class PermissionManager {
    private final Map<String, Set<String>> userPermissions = new HashMap<>();

    public void grantPermission(String user, String permission) {
        userPermissions.computeIfAbsent(user, k -> new HashSet<>()).add(permission);
    }

    public void revokePermission(String user, String permission) {
        Set<String> perms = userPermissions.get(user);
        if (perms != null) perms.remove(permission);
    }

    public boolean hasPermission(String user, String permission) {
        Set<String> perms = userPermissions.get(user);
        return perms != null && perms.contains(permission);
    }
}
