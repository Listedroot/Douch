package com.sunpowder.douch.bootstrap;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class BootstrapPIDManager {
    private final File pidFile;
    public BootstrapPIDManager(File pidFile) {
        this.pidFile = pidFile;
    }
    public void writePID() throws IOException {
        String pid = java.lang.management.ManagementFactory.getRuntimeMXBean().getName().split("@")[0];
        try (FileWriter writer = new FileWriter(pidFile)) {
            writer.write(pid);
        }
    }
    public void cleanup() {
        if (pidFile.exists()) pidFile.delete();
    }
}
