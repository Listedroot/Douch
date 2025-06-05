package com.sunpowder.douch.api;

import java.util.List;

public interface ProxyServer {
    List<String> getOnlinePlayers();
    void broadcast(String message);
    void kickPlayer(String username, String reason);
    void shutdown();
}
