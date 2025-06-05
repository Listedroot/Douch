package com.sunpowder.douch.firewall;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class ConnectionLimiter {
    private final ConcurrentHashMap<String, AtomicInteger> ipCounts = new ConcurrentHashMap<>();
    private final int maxConnectionsPerIp;

    public ConnectionLimiter(int maxConnectionsPerIp) {
        this.maxConnectionsPerIp = maxConnectionsPerIp;
    }

    public boolean allowConnection(String ip) {
        ipCounts.putIfAbsent(ip, new AtomicInteger(0));
        int count = ipCounts.get(ip).incrementAndGet();
        return count <= maxConnectionsPerIp;
    }

    public void connectionClosed(String ip) {
        AtomicInteger count = ipCounts.get(ip);
        if (count != null) {
            count.decrementAndGet();
        }
    }
}
