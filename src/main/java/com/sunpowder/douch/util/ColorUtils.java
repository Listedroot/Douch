package com.sunpowder.douch.util;

public class ColorUtils {
    public static String color(String code, String msg) {
        return "\u00A7" + code + msg;
    }
    public static String strip(String msg) {
        return msg.replaceAll("\u00A7[0-9A-FK-ORa-fk-or]", "");
    }
}
