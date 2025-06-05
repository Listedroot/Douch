package com.sunpowder.douch.security;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

public class EncryptionManager {
    private Cipher encryptCipher;
    private Cipher decryptCipher;

    public void init(byte[] keyBytes) throws Exception {
        Key key = new SecretKeySpec(keyBytes, "AES");
        encryptCipher = Cipher.getInstance("AES");
        encryptCipher.init(Cipher.ENCRYPT_MODE, key);
        decryptCipher = Cipher.getInstance("AES");
        decryptCipher.init(Cipher.DECRYPT_MODE, key);
    }
    public byte[] encrypt(byte[] data) throws Exception {
        return encryptCipher.doFinal(data);
    }
    public byte[] decrypt(byte[] data) throws Exception {
        return decryptCipher.doFinal(data);
    }
}
