package org.briarheart.algorithms.sort;

import org.briarheart.algorithms.sort.impl.AbstractSortEngine;

/**
 * @author Roman Chigvintsev
 */
public class Sort {
    private Sort() {
        //no instance
    }

    public static <T extends Comparable<? super T>> T[] doSort(T[] a) {
        return doSort(a, SortingAlgorithm.SHELL);
    }

    public static <T extends Comparable<? super T>> T[] doSort(T[] a, int lo, int hi) {
        return doSort(a, lo, hi, SortingAlgorithm.SHELL);
    }

    public static <T extends Comparable<? super T>> T[] doSort(T[] a, SortingAlgorithm alg) {
        if (a == null)
            throw new IllegalArgumentException("Array cannot be null");
        return doSort(a, 0, a.length - 1, alg);
    }

    public static <T extends Comparable<? super T>> T[] doSort(T[] a, int lo, int hi, SortingAlgorithm alg) {
        return AbstractSortEngine.<T>create(alg).sort(a, lo, hi);
    }
}
