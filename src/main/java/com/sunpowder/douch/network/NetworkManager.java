package com.sunpowder.douch.network;

import com.sunpowder.douch.core.DouchProxy;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.AttributeKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

public class NetworkManager {
    private static final Logger logger = LoggerFactory.getLogger(NetworkManager.class);
    private static final int DEFAULT_PORT = 25565;
    
    // Attribute key for protocol version
    public static final AttributeKey<Integer> PROTOCOL_VERSION = AttributeKey.newInstance("protocol_version");
    
    private final DouchProxy proxy;
    private final boolean onlineMode;
    private final int port;
    
    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;
    private Channel channel;
    
    public NetworkManager(DouchProxy proxy, boolean onlineMode) {
        this(proxy, onlineMode, DEFAULT_PORT);
    }
    
    public NetworkManager(DouchProxy proxy, boolean onlineMode, int port) {
        this.proxy = proxy;
        this.onlineMode = onlineMode;
        this.port = port;
    }
    
    public void start() throws Exception {
        boolean epoll = Epoll.isAvailable();
        logger.info("Using {} for network I/O", epoll ? "Epoll" : "NIO");
        
        bossGroup = epoll ? new EpollEventLoopGroup(1) : new NioEventLoopGroup(1);
        workerGroup = epoll ? new EpollEventLoopGroup() : new NioEventLoopGroup();
        
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                .channel(epoll ? EpollServerSocketChannel.class : NioServerSocketChannel.class)
                .childHandler(new MCInitialPipeline(proxy, onlineMode))
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childOption(ChannelOption.SO_KEEPALIVE, true);
                
            logger.info("Binding to port {}...", port);
            ChannelFuture future = b.bind(new InetSocketAddress(port)).syncUninterruptibly();
            
            channel = future.channel();
            if (channel != null) {
                logger.info("Proxy listening on {}:{}", 
                    ((InetSocketAddress)channel.localAddress()).getHostString(),
                    ((InetSocketAddress)channel.localAddress()).getPort());
            } else {
                logger.error("Failed to bind to port {}", port);
            }
            
            channel.closeFuture().syncUninterruptibly();
        } finally {
            stop();
        }
    }
    
    public void stop() {
        if (bossGroup != null) {
            bossGroup.shutdownGracefully();
            bossGroup = null;
        }
        
        if (workerGroup != null) {
            workerGroup.shutdownGracefully();
            workerGroup = null;
        }
        
        if (channel != null) {
            channel.close().awaitUninterruptibly();
            channel = null;
        }
        
        logger.info("NetworkManager has been stopped");
    }
    
    public boolean isRunning() {
        return channel != null && channel.isOpen();
    }
}
