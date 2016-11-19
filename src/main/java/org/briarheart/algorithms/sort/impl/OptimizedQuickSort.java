package org.briarheart.algorithms.sort.impl;

import org.briarheart.algorithms.sort.SortingAlgorithm;
import org.briarheart.algorithms.sort.SortEngine;

/**
 * @author Roman Chigvintsev
 */
class OptimizedQuickSort<T extends Comparable<? super T>> extends AbstractSortEngine<T> {
    private static final int INSERTION_SORTING_THRESHOLD = 7;

    private SortEngine<T> insertSortEngine = create(SortingAlgorithm.OPTIMIZED_INSERTION);

    @Override
    protected void doSort(T[] a, int lo, int hi) {
        if (lo + INSERTION_SORTING_THRESHOLD > hi) {
            insertSortEngine.sort(a, lo, hi);
            return;
        }

        int length = hi - lo + 1;

        int n = length <= 40 ? median3(a, lo, lo + length / 2, hi) : tukeyNinther(a, lo, hi);
        exchange(a, n, lo);

        // Bentley-McIlroy 3-way partitioning
        int i = lo, j = hi + 1;
        int p = lo, q = hi + 1;
        T v = a[lo];
        while (true) {
            while (less(a[++i], v)) if (i == hi) break;
            while (less(v, a[--j])) if (j == lo) break;
            if (i == j && eq(a[i], v))
                exchange(a, ++p, i);
            if (i >= j)
                break;
            exchange(a, i, j);
            if (eq(a[i], v))
                exchange(a, ++p, i);
            if (eq(a[j], v))
                exchange(a, --q, j);
        }

        i = j + 1;
        for (int k = lo; k <= p; k++)
            exchange(a, k, j--);
        for (int k = hi; k >= q; k--)
            exchange(a, k, i++);

        doSort(a, lo, j);
        doSort(a, i, hi);
    }

    private int median3(T[] a, int i, int j, int k) {
        return less(a[i], a[j]) ? (less(a[j], a[k]) ? j : (less(a[i], a[k]) ? k : i))
                                : (less(a[k], a[j]) ? j : (less(a[k], a[i]) ? k : i));
    }

    /**
     * Tukey ninther
     */
    private int tukeyNinther(T[] a, int lo, int hi) {
        int length = hi - lo + 1;
        int eps = length / 8;
        int mid = lo + length / 2;
        int m1 = median3(a, lo, lo + eps, lo + eps + eps);
        int m2 = median3(a, mid - eps, mid, mid + eps);
        int m3 = median3(a, hi - eps - eps, hi - eps, hi);
        return median3(a, m1, m2, m3);
    }
}
