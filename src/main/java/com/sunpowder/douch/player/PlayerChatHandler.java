package com.sunpowder.douch.player;

import io.netty.channel.Channel;

public class PlayerChatHandler {
    private final Channel channel;
    public PlayerChatHandler(Channel channel) {
        this.channel = channel;
    }
    public void sendMessage(String message) {
        // Minecraft chat packet (0x0F for modern, 0x02 for legacy, simplified)
        // For demonstration, just send as plain text
        channel.writeAndFlush(message);
    }
}
