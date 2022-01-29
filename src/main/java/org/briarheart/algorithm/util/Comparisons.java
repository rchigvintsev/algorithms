package org.briarheart.algorithm.util;

public class Comparisons {
    private Comparisons() {
        //no instance
    }

    public static <T extends Comparable<? super T>> boolean lt(T a, T b) {
        if (a == null || b == null) {
            return a == null && b != null;
        }
        return a.compareTo(b) < 0;
    }
}
