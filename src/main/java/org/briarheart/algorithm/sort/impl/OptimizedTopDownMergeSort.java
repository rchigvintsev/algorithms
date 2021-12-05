package org.briarheart.algorithm.sort.impl;

import org.briarheart.algorithm.sort.SortEngine;
import org.briarheart.algorithm.sort.SortingAlgorithm;

import java.util.Arrays;

/**
 * @author Roman Chigvintsev
 */
class OptimizedTopDownMergeSort<T extends Comparable<? super T>> extends AbstractMergeSort<T> {
    private static final int INSERTION_SORTING_THRESHOLD = 7;

    private SortEngine<T> insertSortEngine = create(SortingAlgorithm.OPTIMIZED_INSERTION);
    private T[] aux;

    @Override
    protected void beforeSort(T[] a, int lo, int hi) {
        aux = Arrays.copyOf(a, a.length);
    }

    @Override
    protected void doSort(T[] a, int lo, int hi) {
        doSort(aux, a, lo, hi);
    }

    private void doSort(T[] src, T[] dst, int lo, int hi) {
        if (lo + INSERTION_SORTING_THRESHOLD > hi) {
            insertSortEngine.sort(dst, lo, hi);
            return;
        }
        int mid = lo + (hi - lo) / 2;
        doSort(dst, src, lo, mid);
        doSort(dst, src, mid + 1, hi);
        if (!less(src[mid + 1], src[mid])) {
            System.arraycopy(src, lo, dst, lo, hi - lo + 1);
            return;
        }
        merge(src, dst, lo, mid, hi);
    }
}
