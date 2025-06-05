package com.sunpowder.douch.api;

public class ProxyPingEvent implements ProxyEvent {
    private final String address;
    private final long timestamp;
    public ProxyPingEvent(String address) {
        this.address = address;
        this.timestamp = System.currentTimeMillis();
    }
    public String getAddress() { return address; }
    @Override public long getTimestamp() { return timestamp; }
    @Override public String getName() { return "ProxyPingEvent"; }
}
