package com.sunpowder.douch.network;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NetworkManager {
    private static final Logger logger = LoggerFactory.getLogger(NetworkManager.class);
    private static final int DEFAULT_PORT = 25565;

    public void start() throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new MCInitialPipeline());
            logger.info("Binding to port {}...", DEFAULT_PORT);
            ChannelFuture f = b.bind(DEFAULT_PORT).sync();
            logger.info("Proxy listening on port {}", DEFAULT_PORT);
            f.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public void stop() {
        // Stop the Netty server gracefully
        // This is a stub. In a real implementation, store bossGroup and workerGroup as fields and shut them down here.
        // For now, just log the stop attempt.
        logger.info("NetworkManager stop() called (not fully implemented)");
    }
}
