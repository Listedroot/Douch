package com.sunpowder.douch.network.util;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.security.spec.EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class EncryptionUtil {
    private static final String RSA = "RSA";
    private static final String AES = "AES";
    private static final String AES_CFB8 = "AES/CFB8/NoPadding";
    
    public static KeyPair generateKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance(RSA);
        keyGen.initialize(1024);
        return keyGen.generateKeyPair();
    }
    
    public static byte[] decryptRSA(PrivateKey privateKey, byte[] data) throws Exception {
        Cipher cipher = Cipher.getInstance(RSA);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(data);
    }
    
    public static PublicKey decodePublicKey(byte[] keyBytes) throws Exception {
        EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA);
        return keyFactory.generatePublic(keySpec);
    }
    
    public static Cipher createNetCipher(int mode, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance(AES_CFB8);
        cipher.init(mode, key, new IvParameterSpec(key.getEncoded()));
        return cipher;
    }
    
    public static String generateServerId(String base, PublicKey publicKey, SecretKey secretKey) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.update(base.getBytes());
            digest.update(secretKey.getEncoded());
            digest.update(publicKey.getEncoded());
            return bytesToHex(digest.digest());
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate server ID", e);
        }
    }
    
    private static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }
}
