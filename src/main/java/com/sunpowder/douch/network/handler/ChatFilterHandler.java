package com.sunpowder.douch.network.handler;

import com.sunpowder.douch.chat.ChatFilter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ChatFilterHandler extends ChannelInboundHandlerAdapter {
    private final ChatFilter chatFilter;
    public ChatFilterHandler(ChatFilter chatFilter) {
        this.chatFilter = chatFilter;
    }
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof String) {
            String message = (String) msg;
            if (!chatFilter.isAllowed(message)) {
                ctx.writeAndFlush("[Proxy] Message blocked by filter.\n");
                return;
            }
        }
        super.channelRead(ctx, msg);
    }
}
