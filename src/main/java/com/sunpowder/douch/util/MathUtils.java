package com.sunpowder.douch.util;

public class MathUtils {
    public static int clamp(int value, int min, int max) {
        return Math.max(min, Math.min(max, value));
    }
    public static double lerp(double a, double b, double t) {
        return a + (b - a) * t;
    }
}
