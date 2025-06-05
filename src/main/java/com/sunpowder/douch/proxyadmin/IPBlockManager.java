package com.sunpowder.douch.proxyadmin;

import java.util.HashSet;
import java.util.Set;

public class IPBlockManager {
    private final Set<String> blockedIPs = new HashSet<>();

    public void blockIP(String ip) {
        blockedIPs.add(ip);
    }

    public void unblockIP(String ip) {
        blockedIPs.remove(ip);
    }

    public boolean isBlocked(String ip) {
        return blockedIPs.contains(ip);
    }
}
