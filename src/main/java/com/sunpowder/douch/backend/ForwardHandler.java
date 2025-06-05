package com.sunpowder.douch.backend;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.Channel;

public class ForwardHandler extends ChannelInboundHandlerAdapter {
    private final Channel target;
    public ForwardHandler(Channel target) {
        this.target = target;
    }
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        target.writeAndFlush(msg);
    }
    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        target.close();
    }
}
