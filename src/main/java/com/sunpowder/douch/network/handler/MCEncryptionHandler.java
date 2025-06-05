package com.sunpowder.douch.network.handler;

import io.netty.channel.ChannelPipeline;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class MCEncryptionHandler {
    public void addEncryption(ChannelPipeline pipeline, byte[] key) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
        Cipher encrypt = Cipher.getInstance("AES/CFB8/NoPadding");
        Cipher decrypt = Cipher.getInstance("AES/CFB8/NoPadding");
        encrypt.init(Cipher.ENCRYPT_MODE, secretKey, encrypt.getParameters());
        decrypt.init(Cipher.DECRYPT_MODE, secretKey, decrypt.getParameters());
        pipeline.addAfter("encoder", "encrypt", new MCNettyEncrypt(encrypt));
        pipeline.addAfter("decoder", "decrypt", new MCNettyDecrypt(decrypt));
    }
}
