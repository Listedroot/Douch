package com.sunpowder.douch.player;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.UUID;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class PlayerSkinManager {
    private static final String MOJANG_PROFILE_URL = "https://sessionserver.mojang.com/session/minecraft/profile/";
    private static final Gson gson = new Gson();

    public String getSkinUrl(UUID uuid) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) new URL(MOJANG_PROFILE_URL + uuid.toString().replace("-", "")).openConnection();
        conn.setConnectTimeout(3000);
        conn.setReadTimeout(3000);
        conn.setRequestMethod("GET");
        if (conn.getResponseCode() != 200)
            throw new IOException("Profile not found");
        String json = new String(conn.getInputStream().readAllBytes());
        JsonObject obj = gson.fromJson(json, JsonObject.class);
        for (var prop : obj.getAsJsonArray("properties")) {
            JsonObject propObj = prop.getAsJsonObject();
            if ("textures".equals(propObj.get("name").getAsString())) {
                String value = propObj.get("value").getAsString();
                String decoded = new String(Base64.getDecoder().decode(value));
                JsonObject tex = gson.fromJson(decoded, JsonObject.class);
                return tex.getAsJsonObject("textures").getAsJsonObject("SKIN").get("url").getAsString();
            }
        }
        return null;
    }
}
