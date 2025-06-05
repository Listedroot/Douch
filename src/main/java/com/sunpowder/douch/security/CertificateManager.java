package com.sunpowder.douch.security;

import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

public class CertificateManager {
    private KeyStore keyStore;
    public void loadCertificate(String path, String alias, String password) throws Exception {
        keyStore = KeyStore.getInstance("JKS");
        keyStore.load(null, password.toCharArray());
        try (FileInputStream fis = new FileInputStream(path)) {
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            Certificate cert = cf.generateCertificate(fis);
            keyStore.setCertificateEntry(alias, cert);
        }
    }
    public X509Certificate getCertificate(String alias) throws Exception {
        return (X509Certificate) keyStore.getCertificate(alias);
    }
}
