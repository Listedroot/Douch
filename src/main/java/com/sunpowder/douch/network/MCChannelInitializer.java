package com.sunpowder.douch.network;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import com.sunpowder.douch.chat.ChatCommandHandler;
import com.sunpowder.douch.chat.ChatFilter;
import com.sunpowder.douch.chat.ChatManager;
import com.sunpowder.douch.firewall.FirewallManager;
import com.sunpowder.douch.firewall.ConnectionLimiter;
import com.sunpowder.douch.firewall.RateLimiter;
import com.sunpowder.douch.metrics.ConnectionMetrics;
import com.sunpowder.douch.metrics.TrafficMetrics;
import com.sunpowder.douch.metrics.LatencyMetrics;
import com.sunpowder.douch.proxyadmin.AdminCommandHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import com.sunpowder.douch.network.handler.ChatHandler;
import com.sunpowder.douch.network.handler.ChatFilterHandler;
import com.sunpowder.douch.network.handler.ChatCommandHandlerNetty;
import com.sunpowder.douch.network.handler.AdminCommandHandlerNetty;
import com.sunpowder.douch.network.handler.MCHandshakeHandler;
import com.sunpowder.douch.network.handler.MCLoginHandler;
import com.sunpowder.douch.network.handler.MCStatusHandler;
import com.sunpowder.douch.network.handler.MCCompressionHandler;
import com.sunpowder.douch.network.handler.MCEncryptionHandler;
import com.sunpowder.douch.network.handler.MCIdleHandler;
import com.sunpowder.douch.network.handler.MCHandshakeDecoder;
import com.sunpowder.douch.network.handler.MCDecoder;
import com.sunpowder.douch.network.handler.MCEncoder;
import com.sunpowder.douch.network.handler.MCNettyEncrypt;
import com.sunpowder.douch.network.handler.MCNettyDecrypt;

public class MCChannelInitializer extends ChannelInitializer<Channel> {
    private final FirewallManager firewallManager = new FirewallManager();
    private final ConnectionLimiter connectionLimiter = new ConnectionLimiter(10);
    private final RateLimiter rateLimiter = new RateLimiter(1000);
    private final ConnectionMetrics connectionMetrics = new ConnectionMetrics();
    private final TrafficMetrics trafficMetrics = new TrafficMetrics();
    private final LatencyMetrics latencyMetrics = new LatencyMetrics();
    private final ChatManager chatManager = new ChatManager();
    private final ChatFilter chatFilter = new ChatFilter();
    private final ChatCommandHandler chatCommandHandler = new ChatCommandHandler();
    private final AdminCommandHandler adminCommandHandler = new AdminCommandHandler();

    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        // Framing (for Minecraft packet structure)
        pipeline.addLast("frame-decoder", new LengthFieldBasedFrameDecoder(65535, 0, 2, 0, 2));
        pipeline.addLast("frame-encoder", new LengthFieldPrepender(2));
        // Minecraft protocol handlers
        pipeline.addLast("decoder", new MCDecoder());
        pipeline.addLast("encoder", new MCEncoder());
        pipeline.addLast("handshake-decoder", new MCHandshakeDecoder());
        pipeline.addLast("handshake-handler", new MCHandshakeHandler());
        // Idle connection handler
        pipeline.addLast("idle-handler", new MCIdleHandler());
        // Firewall and rate limiting
        pipeline.addLast("firewall", new ChannelInboundHandlerAdapter() {
            @Override
            public void channelActive(ChannelHandlerContext ctx) throws Exception {
                String ip = ctx.channel().remoteAddress().toString();
                if (firewallManager.isBlocked(ip) || !connectionLimiter.allowConnection(ip)) {
                    ctx.close();
                    return;
                }
                connectionMetrics.incrementActive();
                super.channelActive(ctx);
            }
            @Override
            public void channelInactive(ChannelHandlerContext ctx) throws Exception {
                String ip = ctx.channel().remoteAddress().toString();
                connectionLimiter.connectionClosed(ip);
                connectionMetrics.decrementActive();
                super.channelInactive(ctx);
            }
        });
        pipeline.addLast("rate-limiter", new ChannelInboundHandlerAdapter() {
            @Override
            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                String ip = ctx.channel().remoteAddress().toString();
                if (!rateLimiter.allow(ip)) {
                    ctx.close();
                    return;
                }
                super.channelRead(ctx, msg);
            }
        });
        // Metrics collection
        pipeline.addLast("metrics", new ChannelInboundHandlerAdapter() {
            @Override
            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                trafficMetrics.addBytesRead(msg.toString().getBytes().length);
                // Add more detailed packet/latency metrics here as needed
                super.channelRead(ctx, msg);
            }
        });
        
        pipeline.addLast("chat-manager", new ChatHandler(chatManager));
        pipeline.addLast("chat-filter", new ChatFilterHandler(chatFilter));
        pipeline.addLast("chat-command-handler", new ChatCommandHandlerNetty(chatCommandHandler));
        // Admin commands
        pipeline.addLast("admin-command-handler", new AdminCommandHandlerNetty(adminCommandHandler));
        // Remove placeholder
        // Handlers for login and status are dynamically added by MCHandshakeHandler
    }
}
