package com.sunpowder.douch.util;

public class TextUtils {
    public static String colorize(String msg) {
        return msg.replace('&', '\u00A7');
    }
    public static String stripColors(String msg) {
        return msg.replaceAll("\u00A7[0-9A-FK-ORa-fk-or]", "");
    }
}
