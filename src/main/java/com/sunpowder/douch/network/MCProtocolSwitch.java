package com.sunpowder.douch.network;

import io.netty.channel.Channel;

public class MCProtocolSwitch {
    private final Channel channel;
    public MCProtocolSwitch(Channel channel) {
        this.channel = channel;
    }
    public void switchToPlay() {
        // Switch to play protocol pipeline
        // Implementation would update Netty pipeline handlers
    }
    public void switchToLogin() {
        // Switch to login protocol pipeline
        // Implementation would update Netty pipeline handlers
    }
}
