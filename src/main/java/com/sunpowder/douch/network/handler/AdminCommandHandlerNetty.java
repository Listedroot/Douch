package com.sunpowder.douch.network.handler;

import com.sunpowder.douch.proxyadmin.AdminCommandHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class AdminCommandHandlerNetty extends ChannelInboundHandlerAdapter {
    private final AdminCommandHandler adminCommandHandler;
    public AdminCommandHandlerNetty(AdminCommandHandler adminCommandHandler) {
        this.adminCommandHandler = adminCommandHandler;
    }
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof String) {
            String message = (String) msg;
            if (message.startsWith("/admin ")) {
                boolean handled = adminCommandHandler.executeCommand(message.substring(7));
                if (handled) {
                    ctx.writeAndFlush("[Proxy] Admin command executed.\n");
                    return;
                }
            }
        }
        super.channelRead(ctx, msg);
    }
}
