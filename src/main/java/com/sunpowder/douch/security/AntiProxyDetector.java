package com.sunpowder.douch.security;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class AntiProxyDetector {
    private static final String CHECK_URL = "https://proxycheck.io/v2/";
    private static final String API_KEY = ""; // Set your API key if required

    public boolean isProxy(String ip) throws IOException {
        URL url = new URL(CHECK_URL + ip + "?vpn=1&asn=1&key=" + API_KEY);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(3000);
        conn.setReadTimeout(3000);
        conn.setRequestMethod("GET");
        try (Scanner scanner = new Scanner(conn.getInputStream())) {
            StringBuilder sb = new StringBuilder();
            while (scanner.hasNextLine()) sb.append(scanner.nextLine());
            String response = sb.toString();
            return response.contains("\"proxy\": \"yes\"") || response.contains("\"vpn\": \"yes\"");
        }
    }
}
