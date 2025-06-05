package com.sunpowder.douch.network.handler;

import io.netty.handler.codec.compression.ZlibCodecFactory;
import io.netty.handler.codec.compression.ZlibWrapper;
import io.netty.handler.codec.compression.JdkZlibEncoder;
import io.netty.handler.codec.compression.JdkZlibDecoder;
import io.netty.channel.ChannelPipeline;

public class MCCompressionHandler {
    public void addCompression(ChannelPipeline pipeline, int threshold) {
        pipeline.addAfter("decoder", "decompress", new JdkZlibDecoder(ZlibWrapper.ZLIB));
        pipeline.addAfter("encoder", "compress", new JdkZlibEncoder(ZlibWrapper.ZLIB, threshold));
    }
}
