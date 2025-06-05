package com.sunpowder.douch.network;

import com.sunpowder.douch.core.DouchProxy;
import com.sunpowder.douch.network.handler.*;
import com.sunpowder.douch.player.PlayerAuthenticator;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;

import java.util.concurrent.TimeUnit;

public class MCInitialPipeline extends ChannelInitializer<SocketChannel> {
    private final DouchProxy proxy;
    private final boolean onlineMode;
    
    public MCInitialPipeline(DouchProxy proxy, boolean onlineMode) {
        this.proxy = proxy;
        this.onlineMode = onlineMode;
    }
    
    @Override
    protected void initChannel(SocketChannel ch) {
        ChannelPipeline pipeline = ch.pipeline();
        
        // Timeout handler (30 seconds)
        pipeline.addLast("timeout", new ReadTimeoutHandler(30, TimeUnit.SECONDS));
        
        // Frame decoder/encoder for packet length
        pipeline.addLast("splitter", new MCFrameDecoder());
        pipeline.addLast("prepender", new MCFramePrepender());
        
        // Packet compression (threshold of 256 bytes)
        pipeline.addLast("compressor", new MCCompressionHandler(256));
        
        // Handshake handler
        pipeline.addLast("handshake-decoder", new MCHandshakeDecoder());
        pipeline.addLast("handshake-handler", new MCHandshakeHandler(proxy, onlineMode));
    }
}
