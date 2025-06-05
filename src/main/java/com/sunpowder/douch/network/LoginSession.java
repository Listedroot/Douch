package com.sunpowder.douch.network;

import io.netty.channel.Channel;

import javax.crypto.SecretKey;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Arrays;

public class LoginSession {
    private final Channel channel;
    private String username;
    private byte[] verifyToken;
    private SecretKey secretKey;
    private KeyPair keyPair;
    private boolean onlineMode;
    
    public LoginSession(Channel channel) {
        this.channel = channel;
        this.verifyToken = new byte[4];
        new java.security.SecureRandom().nextBytes(this.verifyToken);
    }
    
    public Channel getChannel() {
        return channel;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public byte[] getVerifyToken() {
        return verifyToken;
    }
    
    public SecretKey getSecretKey() {
        return secretKey;
    }
    
    public void setSecretKey(SecretKey secretKey) {
        this.secretKey = secretKey;
    }
    
    public KeyPair getKeyPair() {
        return keyPair;
    }
    
    public void setKeyPair(KeyPair keyPair) {
        this.keyPair = keyPair;
    }
    
    public boolean isOnlineMode() {
        return onlineMode;
    }
    
    public void setOnlineMode(boolean onlineMode) {
        this.onlineMode = onlineMode;
    }
    
    public boolean verifyToken(byte[] receivedToken) {
        return Arrays.equals(verifyToken, receivedToken);
    }
}
