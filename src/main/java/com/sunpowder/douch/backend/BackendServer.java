package com.sunpowder.douch.backend;

public class BackendServer {
    private final String name;
    private final String address;
    private final int port;
    public BackendServer(String name, String address, int port) {
        this.name = name;
        this.address = address;
        this.port = port;
    }
    public String getName() { return name; }
    public String getAddress() { return address; }
    public int getPort() { return port; }
}
