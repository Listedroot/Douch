package com.sunpowder.douch.security;

import java.net.InetAddress;
import java.util.HashSet;
import java.util.Set;

public class ConnectionFirewall {
    private final Set<String> blockedIps = new HashSet<>();

    public void blockIp(String ip) {
        blockedIps.add(ip);
    }
    public void unblockIp(String ip) {
        blockedIps.remove(ip);
    }
    public boolean isBlocked(InetAddress address) {
        return blockedIps.contains(address.getHostAddress());
    }
}
