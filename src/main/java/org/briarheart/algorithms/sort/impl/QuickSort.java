package org.briarheart.algorithms.sort.impl;

import java.util.Random;

/**
 * Quicksort is developed by Tony Hoare in 1959.
 * <p>
 * This algorithm has O(<i>n</i><sup>2</sup>) time complexity in the worst case and O(<i>n</i>log<i>n</i>)
 * in the average case.
 *
 * @author Roman Chigvintsev
 */
class QuickSort<T extends Comparable<? super T>> extends AbstractSortEngine<T> {
    private Random random = new Random();

    @Override
    protected void beforeSort(T[] a, int lo, int hi) {
        shuffle(a, lo, hi);
    }

    @Override
    protected void doSort(T[] a, int lo, int hi) {
        if (lo >= hi)
            return;
        int j = partition(a, lo, hi);
        doSort(a, lo, j - 1);
        doSort(a, j + 1, hi);
    }

    private int partition(T[] a, int lo, int hi) {
        T v = a[lo];
        int i = lo;
        int j = hi + 1;
        while (true) {
            while (less(a[++i], v)) if (i == hi) break;
            while (less(v, a[--j])) if (j == lo) break;
            if (i >= j)
                break;
            exchange(a, i, j);
        }
        exchange(a, lo, j);
        return j;
    }

    private void shuffle(T[] a, int lo, int hi) {
        for (int i = lo; i < hi; i++) {
            int r = i + random.nextInt(hi - i + 1);
            exchange(a, i, r);
        }
    }
}
