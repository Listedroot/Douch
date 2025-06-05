package com.sunpowder.douch.network.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.compression.JdkZlibDecoder;
import io.netty.handler.codec.compression.JdkZlibEncoder;
import io.netty.handler.codec.compression.ZlibWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MCCompressionHandler extends ChannelInboundHandlerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(MCCompressionHandler.class);
    private final int threshold;

    public MCCompressionHandler(int threshold) {
        this.threshold = threshold;
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        // Add compression to the pipeline
        ctx.pipeline().addAfter("splitter", "decompress", new JdkZlibDecoder(ZlibWrapper.ZLIB));
        ctx.pipeline().addAfter("prepender", "compress", new JdkZlibEncoder(ZlibWrapper.ZLIB, threshold));
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        // Clean up compression handlers
        if (ctx.pipeline().get("decompress") != null) {
            ctx.pipeline().remove("decompress");
        }
        if (ctx.pipeline().get("compress") != null) {
            ctx.pipeline().remove("compress");
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        logger.error("Error in compression handler", cause);
        ctx.close();
    }
}
