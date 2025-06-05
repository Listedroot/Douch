package com.sunpowder.douch.firewall;

import java.util.HashSet;
import java.util.Set;

public class ProtocolBlocker {
    private final Set<String> blockedProtocols = new HashSet<>();

    public void blockProtocol(String protocol) {
        blockedProtocols.add(protocol.toLowerCase());
    }

    public void unblockProtocol(String protocol) {
        blockedProtocols.remove(protocol.toLowerCase());
    }

    public boolean isBlocked(String protocol) {
        return blockedProtocols.contains(protocol.toLowerCase());
    }
}
