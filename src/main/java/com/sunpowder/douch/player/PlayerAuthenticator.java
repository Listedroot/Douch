package com.sunpowder.douch.player;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class PlayerAuthenticator {
    private static final String MOJANG_PROFILE_URL = "https://api.mojang.com/users/profiles/minecraft/";
    private static final Gson gson = new Gson();

    /**
     * Authenticate a player using Mojang's API (online mode).
     * Returns true if the username exists and is valid.
     */
    public boolean authenticateOnline(String username) {
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(MOJANG_PROFILE_URL + username).openConnection();
            conn.setConnectTimeout(3000);
            conn.setReadTimeout(3000);
            conn.setRequestMethod("GET");
            int code = conn.getResponseCode();
            return code == 200;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Get the UUID for a player in online mode (from Mojang).
     */
    public UUID getUUIDOnline(String username) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) new URL(MOJANG_PROFILE_URL + username).openConnection();
        conn.setConnectTimeout(3000);
        conn.setReadTimeout(3000);
        conn.setRequestMethod("GET");
        if (conn.getResponseCode() != 200)
            throw new IOException("Player not found");
        String json = new String(conn.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        JsonObject obj = gson.fromJson(json, JsonObject.class);
        String id = obj.get("id").getAsString();
        return parseUUIDWithoutDashes(id);
    }

    /**
     * Get the UUID for a player in offline mode (deterministic algorithm).
     */
    public UUID getUUIDOffline(String username) {
        return UUID.nameUUIDFromBytes(("OfflinePlayer:" + username).getBytes(StandardCharsets.UTF_8));
    }

    private UUID parseUUIDWithoutDashes(String uuid) {
        return UUID.fromString(uuid.replaceFirst(
            "(\\w{8})(\\w{4})(\\w{4})(\\w{4})(\\w{12})",
            "$1-$2-$3-$4-$5"
        ));
    }
}
