package com.sunpowder.douch.network.handler;

import com.sunpowder.douch.chat.ChatManager;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ChatHandler extends ChannelInboundHandlerAdapter {
    private final ChatManager chatManager;
    public ChatHandler(ChatManager chatManager) {
        this.chatManager = chatManager;
    }
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // Example: handle chat messages
        if (msg instanceof String) {
            String message = (String) msg;
            chatManager.addMessage(ctx.channel().remoteAddress().toString(), message);
        }
        super.channelRead(ctx, msg);
    }
}
