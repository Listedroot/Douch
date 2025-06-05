package com.sunpowder.douch.core;

import java.util.HashMap;
import java.util.Map;

public class ProxyCommandManager {
    private final Map<String, ProxyCommand> commands = new HashMap<>();
    public void register(String name, ProxyCommand command) {
        commands.put(name.toLowerCase(), command);
    }
    public void execute(String name, String[] args) {
        ProxyCommand command = commands.get(name.toLowerCase());
        if (command != null) command.execute(args);
    }
    public interface ProxyCommand {
        void execute(String[] args);
    }
}
