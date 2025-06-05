package com.sunpowder.douch.firewall;

import java.util.HashSet;
import java.util.Set;

public class ASNBlocker {
    private final Set<Integer> blockedAsns = new HashSet<>();

    public void blockAsn(int asn) {
        blockedAsns.add(asn);
    }

    public void unblockAsn(int asn) {
        blockedAsns.remove(asn);
    }

    public boolean isBlocked(int asn) {
        return blockedAsns.contains(asn);
    }
}
