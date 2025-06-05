package com.sunpowder.douch.player;

import com.sunpowder.douch.player.PlayerManager;
import com.sunpowder.douch.player.PlayerSession;
import com.sunpowder.douch.player.PlayerAuthenticator;
import java.net.InetSocketAddress;
import java.util.UUID;
import io.netty.channel.Channel;

public class PlayerLoginHandler {
    private final PlayerManager playerManager;
    private final PlayerAuthenticator authenticator;

    public PlayerLoginHandler(PlayerManager playerManager, PlayerAuthenticator authenticator) {
        this.playerManager = playerManager;
        this.authenticator = authenticator;
    }

    public boolean login(String username, String ip, Channel channel, boolean onlineMode) {
        UUID uuid;
        try {
            if (onlineMode) {
                if (!authenticator.authenticateOnline(username)) return false;
                uuid = authenticator.getUUIDOnline(username);
            } else {
                uuid = authenticator.getUUIDOffline(username);
            }
        } catch (Exception e) {
            return false;
        }
        PlayerSession session = new PlayerSession(username, uuid, new InetSocketAddress(ip, 0), channel);
        playerManager.addSession(session);
        return true;
    }
}
