package org.briarheart.algorithms.util;

/**
 * @author Roman Chigvintsev
 */
public class Arrays {
    private Arrays() {}

    public static Integer[] wrapPrimitives(int[] a) {
        if (a == null)
            throw new IllegalArgumentException("Array cannot be null");
        Integer[] result = new Integer[a.length];
        for (int i = 0; i < a.length; i++)
            result[i] = a[i];
        return result;
    }
}
