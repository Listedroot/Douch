package com.sunpowder.douch.auth;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sunpowder.douch.network.util.EncryptionUtil;

import javax.crypto.SecretKey;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.PublicKey;
import java.util.UUID;

public class SessionService {
    private static final String SESSION_SERVER = "https://sessionserver.mojang.com/session/minecraft/hasJoined";
    
    public ProfileInfo verifySession(String username, String serverId) throws IOException {
        String url = String.format("%s?username=%s&serverId=%s", 
                SESSION_SERVER, username, serverId);
        
        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
        conn.setRequestMethod("GET");
        
        if (conn.getResponseCode() != 200) {
            throw new IOException("Failed to verify session: HTTP " + conn.getResponseCode());
        }
        
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            
            JsonObject json = JsonParser.parseString(response.toString()).getAsJsonObject();
            String uuid = json.get("id").getAsString();
            String name = json.get("name").getAsString();
            
            return new ProfileInfo(UUID.fromString(formatUuid(uuid)), name);
        }
    }
    
    private String formatUuid(String uuid) {
        // Convert from compact UUID (32 chars) to standard UUID format (with hyphens)
        return uuid.replaceFirst(
                "(\\w{8})(\\w{4})(\\w{4})(\\w{4})(\\w{12})",
                "$1-$2-$3-$4-$5");
    }
    
    public static class ProfileInfo {
        public final UUID uuid;
        public final String username;
        
        public ProfileInfo(UUID uuid, String username) {
            this.uuid = uuid;
            this.username = username;
        }
    }
}
