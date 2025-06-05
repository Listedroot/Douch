package com.sunpowder.douch.network.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import java.util.List;

public class MCHandshakeDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (!in.isReadable()) return;
        in.markReaderIndex();
        int packetLength = readVarInt(in);
        int startReaderIndex = in.readerIndex();
        if (in.readableBytes() < packetLength) {
            in.resetReaderIndex();
            return;
        }
        int packetId = readVarInt(in);
        if (packetId == 0x00) { // Handshake
            int protocolVersion = readVarInt(in);
            String serverAddress = readString(in);
            int serverPort = in.readUnsignedShort();
            int nextState = readVarInt(in);
            out.add(new HandshakePacket(protocolVersion, serverAddress, serverPort, nextState));
        } else {
            int bytesRead = in.readerIndex() - startReaderIndex;
            int skipBytes = packetLength - bytesRead;
            if (skipBytes > 0) {
                in.skipBytes(skipBytes);
            }
        }
    }

    private int readVarInt(ByteBuf buf) {
        int numRead = 0, result = 0;
        byte read;
        do {
            read = buf.readByte();
            int value = (read & 0b01111111);
            result |= (value << (7 * numRead));
            numRead++;
            if (numRead > 5) throw new RuntimeException("VarInt too big");
        } while ((read & 0b10000000) != 0);
        return result;
    }

    private String readString(ByteBuf buf) {
        int len = readVarInt(buf);
        byte[] bytes = new byte[len];
        buf.readBytes(bytes);
        return new String(bytes);
    }

    public static class HandshakePacket {
        public final int protocolVersion;
        public final String serverAddress;
        public final int serverPort;
        public final int nextState;
        public HandshakePacket(int pv, String sa, int sp, int ns) {
            this.protocolVersion = pv;
            this.serverAddress = sa;
            this.serverPort = sp;
            this.nextState = ns;
        }
    }
}
