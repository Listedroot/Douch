package com.sunpowder.douch.network.packet.login;

import io.netty.buffer.ByteBuf;
import com.sunpowder.douch.network.util.PacketUtil;

public class ServerboundEncryptionResponsePacket {
    public final byte[] sharedSecret;
    public final byte[] verifyToken;
    
    public ServerboundEncryptionResponsePacket(ByteBuf buf) {
        this.sharedSecret = PacketUtil.readByteArray(buf);
        this.verifyToken = PacketUtil.readByteArray(buf);
    }
}
