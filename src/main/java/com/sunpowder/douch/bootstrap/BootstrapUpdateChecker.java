package com.sunpowder.douch.bootstrap;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class BootstrapUpdateChecker {
    private static final String UPDATE_URL = "https://api.github.com/repos/Sunpowder/Douch/releases/latest";
    public String checkForUpdate() throws IOException {
        URL url = new URL(UPDATE_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(3000);
        conn.setReadTimeout(3000);
        conn.setRequestMethod("GET");
        try (Scanner scanner = new Scanner(conn.getInputStream())) {
            StringBuilder sb = new StringBuilder();
            while (scanner.hasNextLine()) sb.append(scanner.nextLine());
            return sb.toString();
        }
    }
}
