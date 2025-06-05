package com.sunpowder.douch.network.handler;

import com.sunpowder.douch.chat.ChatCommandHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ChatCommandHandlerNetty extends ChannelInboundHandlerAdapter {
    private final ChatCommandHandler chatCommandHandler;
    public ChatCommandHandlerNetty(ChatCommandHandler chatCommandHandler) {
        this.chatCommandHandler = chatCommandHandler;
    }
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof String) {
            String message = (String) msg;
            if (message.startsWith("/")) {
                boolean handled = chatCommandHandler.executeCommand(message.substring(1));
                if (handled) {
                    ctx.writeAndFlush("[Proxy] Command executed.\n");
                    return;
                }
            }
        }
        super.channelRead(ctx, msg);
    }
}
