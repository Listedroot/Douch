package com.sunpowder.douch.util;

import java.util.Collection;

public class CollectionUtils {
    public static boolean isEmpty(Collection<?> c) {
        return c == null || c.isEmpty();
    }
    public static <T> T first(Collection<T> c) {
        return c == null || c.isEmpty() ? null : c.iterator().next();
    }
}
