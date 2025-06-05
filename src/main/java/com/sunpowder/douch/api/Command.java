package com.sunpowder.douch.api;

import java.util.List;

public interface Command {
    String getName();
    String getDescription();
    List<String> getAliases();
    void execute(CommandSender sender, String[] args);
}
