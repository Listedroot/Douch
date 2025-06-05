package com.sunpowder.douch.firewall;

import java.util.HashSet;
import java.util.Set;

public class WhitelistManager {
    private final Set<String> whitelist = new HashSet<>();

    public void add(String value) {
        whitelist.add(value.toLowerCase());
    }

    public void remove(String value) {
        whitelist.remove(value.toLowerCase());
    }

    public boolean isWhitelisted(String value) {
        return whitelist.contains(value.toLowerCase());
    }
}
