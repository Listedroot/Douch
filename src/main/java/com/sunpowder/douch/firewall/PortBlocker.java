package com.sunpowder.douch.firewall;

import java.util.HashSet;
import java.util.Set;

public class PortBlocker {
    private final Set<Integer> blockedPorts = new HashSet<>();

    public void blockPort(int port) {
        blockedPorts.add(port);
    }

    public void unblockPort(int port) {
        blockedPorts.remove(port);
    }

    public boolean isBlocked(int port) {
        return blockedPorts.contains(port);
    }
}
