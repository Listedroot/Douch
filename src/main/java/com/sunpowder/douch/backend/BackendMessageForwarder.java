package com.sunpowder.douch.backend;

import io.netty.channel.Channel;

public class BackendMessageForwarder {
    public void forwardMessage(Channel from, Channel to, Object msg) {
        to.writeAndFlush(msg);
    }
}
