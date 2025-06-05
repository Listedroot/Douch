package com.sunpowder.douch.bootstrap;

import java.util.List;

public class BootstrapDependencyChecker {
    public boolean checkDependencies(List<String> requiredJars) {
        for (String jar : requiredJars) {
            try {
                Class.forName(jar);
            } catch (ClassNotFoundException e) {
                System.err.println("Missing dependency: " + jar);
                return false;
            }
        }
        return true;
    }
}
