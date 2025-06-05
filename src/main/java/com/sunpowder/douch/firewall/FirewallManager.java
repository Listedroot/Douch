package com.sunpowder.douch.firewall;

import java.util.HashSet;
import java.util.Set;

public class FirewallManager {
    private final Set<String> blockedIps = new HashSet<>();

    public void blockIp(String ip) {
        blockedIps.add(ip);
    }

    public void unblockIp(String ip) {
        blockedIps.remove(ip);
    }

    public boolean isBlocked(String ip) {
        return blockedIps.contains(ip);
    }
}
