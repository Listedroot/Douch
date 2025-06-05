package com.sunpowder.douch.api;

import java.util.Set;

public interface PermissionManager {
    void addPermission(String username, String permission);
    void removePermission(String username, String permission);
    boolean hasPermission(String username, String permission);
    Set<String> getPermissions(String username);
}
