package com.sunpowder.douch.api;

public interface ProxyEventListener<T extends ProxyEvent> {
    void onEvent(T event);
}
