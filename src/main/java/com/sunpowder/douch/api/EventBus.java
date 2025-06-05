package com.sunpowder.douch.api;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class EventBus {
    private final Map<Class<?>, CopyOnWriteArrayList<ProxyEventListener<?>>> listeners = new ConcurrentHashMap<>();

    public <T extends ProxyEvent> void registerListener(Class<T> type, ProxyEventListener<T> listener) {
        listeners.computeIfAbsent(type, k -> new CopyOnWriteArrayList<>()).add(listener);
    }

    public <T extends ProxyEvent> void fireEvent(T event) {
        CopyOnWriteArrayList<ProxyEventListener<?>> ls = listeners.get(event.getClass());
        if (ls != null) {
            for (ProxyEventListener<?> l : ls) {
                @SuppressWarnings("unchecked")
                ProxyEventListener<T> listener = (ProxyEventListener<T>) l;
                listener.onEvent(event);
            }
        }
    }
}
