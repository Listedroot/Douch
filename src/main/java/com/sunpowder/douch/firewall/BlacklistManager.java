package com.sunpowder.douch.firewall;

import java.util.HashSet;
import java.util.Set;

public class BlacklistManager {
    private final Set<String> blacklist = new HashSet<>();

    public void add(String value) {
        blacklist.add(value.toLowerCase());
    }

    public void remove(String value) {
        blacklist.remove(value.toLowerCase());
    }

    public boolean isBlacklisted(String value) {
        return blacklist.contains(value.toLowerCase());
    }
}
