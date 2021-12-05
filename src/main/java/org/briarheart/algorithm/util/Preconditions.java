package org.briarheart.algorithm.util;

public class Preconditions {
    private Preconditions() {
        //no instance
    }

    public static void notNull(Object value, String message) {
        if (value == null) {
            throw new IllegalArgumentException(message);
        }
    }
}
