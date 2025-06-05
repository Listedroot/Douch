package com.sunpowder.douch.api;

public class CommandRegisterEvent implements ProxyEvent {
    private final String command;
    private final long timestamp;
    public CommandRegisterEvent(String command) {
        this.command = command;
        this.timestamp = System.currentTimeMillis();
    }
    public String getCommand() { return command; }
    @Override public long getTimestamp() { return timestamp; }
    @Override public String getName() { return "CommandRegisterEvent"; }
}
