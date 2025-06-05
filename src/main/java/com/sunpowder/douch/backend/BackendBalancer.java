package com.sunpowder.douch.backend;

import java.util.List;
import java.util.Random;

public class BackendBalancer {
    private final Random random = new Random();
    public String selectServer(List<String> servers) {
        if (servers.isEmpty()) return null;
        return servers.get(random.nextInt(servers.size()));
    }
}
