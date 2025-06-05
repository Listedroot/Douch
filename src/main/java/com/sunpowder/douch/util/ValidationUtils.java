package com.sunpowder.douch.util;

public class ValidationUtils {
    public static boolean isUsernameValid(String username) {
        return username != null && username.matches("[A-Za-z0-9_]{3,16}");
    }
    public static boolean isPortValid(int port) {
        return port >= 1 && port <= 65535;
    }
}
