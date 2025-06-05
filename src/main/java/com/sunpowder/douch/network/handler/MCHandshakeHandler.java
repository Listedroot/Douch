package com.sunpowder.douch.network.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sunpowder.douch.network.handler.MCHandshakeDecoder.HandshakePacket;

public class MCHandshakeHandler extends SimpleChannelInboundHandler<MCHandshakeDecoder.HandshakePacket> {
    private static final Logger logger = LoggerFactory.getLogger(MCHandshakeHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MCHandshakeDecoder.HandshakePacket msg) {
        logger.info("Received handshake: version={}, address={}, port={}, nextState={}",
                msg.protocolVersion, msg.serverAddress, msg.serverPort, msg.nextState);
        // Version detection and pipeline switching
        if (msg.nextState == 1) {
            // Status request
            ctx.pipeline().replace(this, "status-handler", new MCStatusHandler(msg.protocolVersion));
        } else if (msg.nextState == 2) {
            // Login request
            ctx.pipeline().replace(this, "login-handler", new MCLoginHandler(msg.protocolVersion));
        } else {
            logger.error("Unknown next state: {}", msg.nextState);
            ctx.close();
        }
    }
}
