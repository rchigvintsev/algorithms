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

    /**
     * Checks if the given value is greater than or equal to zero.
     *
     * @param value value to check
     * @param message error message
     * @throws IllegalArgumentException if the given value is less than zero
     */
    public static void notNegative(int value, String message) {
        if (value < 0) {
            throw new IllegalArgumentException(message);
        }
    }
}
