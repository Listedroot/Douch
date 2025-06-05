package com.sunpowder.douch.api;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ProxyCommandManager {
    private final Map<String, Command> commands = new ConcurrentHashMap<>();
    public void registerCommand(Command command) {
        commands.put(command.getName(), command);
    }
    public void executeCommand(String name, CommandSender sender, String[] args) {
        Command cmd = commands.get(name);
        if (cmd != null) cmd.execute(sender, args);
    }
    public boolean isRegistered(String name) {
        return commands.containsKey(name);
    }
}
