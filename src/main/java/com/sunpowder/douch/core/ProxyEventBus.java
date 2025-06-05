package com.sunpowder.douch.core;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ProxyEventBus {
    private final List<ProxyEventListener> listeners = new CopyOnWriteArrayList<>();
    public void register(ProxyEventListener listener) {
        listeners.add(listener);
    }
    public void unregister(ProxyEventListener listener) {
        listeners.remove(listener);
    }
    public void fire(ProxyEvent event) {
        for (ProxyEventListener listener : listeners) {
            listener.handle(event);
        }
    }
    public interface ProxyEventListener {
        void handle(ProxyEvent event);
    }
    public static class ProxyEvent {
        private final String type;
        private final String data;
        public ProxyEvent(String type, String data) {
            this.type = type;
            this.data = data;
        }
        public String getType() { return type; }
        public String getData() { return data; }
    }
}
