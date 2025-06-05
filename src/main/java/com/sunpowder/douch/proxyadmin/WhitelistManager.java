package com.sunpowder.douch.proxyadmin;

import java.util.HashSet;
import java.util.Set;

public class WhitelistManager {
    private final Set<String> whitelist = new HashSet<>();

    public void add(String username) {
        whitelist.add(username.toLowerCase());
    }

    public void remove(String username) {
        whitelist.remove(username.toLowerCase());
    }

    public boolean isWhitelisted(String username) {
        return whitelist.contains(username.toLowerCase());
    }
}
