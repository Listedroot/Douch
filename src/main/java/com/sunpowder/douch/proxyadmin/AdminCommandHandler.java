package com.sunpowder.douch.proxyadmin;

import java.util.HashMap;
import java.util.Map;

public class AdminCommandHandler {
    private final Map<String, Runnable> commands = new HashMap<>();

    public void registerCommand(String command, Runnable action) {
        commands.put(command.toLowerCase(), action);
    }

    public boolean executeCommand(String command) {
        Runnable action = commands.get(command.toLowerCase());
        if (action != null) {
            action.run();
            return true;
        }
        return false;
    }
}
