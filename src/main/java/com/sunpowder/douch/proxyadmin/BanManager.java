package com.sunpowder.douch.proxyadmin;

import java.util.HashSet;
import java.util.Set;

public class BanManager {
    private final Set<String> bannedUsers = new HashSet<>();

    public void ban(String username) {
        bannedUsers.add(username.toLowerCase());
    }

    public void unban(String username) {
        bannedUsers.remove(username.toLowerCase());
    }

    public boolean isBanned(String username) {
        return bannedUsers.contains(username.toLowerCase());
    }
}
