package com.sunpowder.douch.backend;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class BackendPing {
    public static boolean ping(String address, int port, int timeoutMillis) {
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress(address, port), timeoutMillis);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
