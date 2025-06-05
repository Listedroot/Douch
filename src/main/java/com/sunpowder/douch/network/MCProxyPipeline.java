package com.sunpowder.douch.network;

import io.netty.channel.ChannelPipeline;

public class MCProxyPipeline {
    public void setup(ChannelPipeline pipeline) {
        // Add handlers for handshake, login, play, etc.
        // Handlers must be added in correct order for Minecraft protocol
    }
}
