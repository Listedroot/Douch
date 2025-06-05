package com.sunpowder.douch.backend;

import io.netty.channel.Channel;

public class BackendForwarder {
    public void forward(Channel client, Channel backend) {
        client.pipeline().addLast(new ForwardHandler(backend));
        backend.pipeline().addLast(new ForwardHandler(client));
    }
}
