package com.sunpowder.douch.api;

public interface ProxyLogger {
    void info(String msg);
    void warn(String msg);
    void error(String msg);
    void debug(String msg);
}
