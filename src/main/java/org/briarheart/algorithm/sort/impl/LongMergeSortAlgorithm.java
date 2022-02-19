package org.briarheart.algorithm.sort.impl;

import org.briarheart.algorithm.sort.LongSortAlgorithm;
import org.briarheart.algorithm.sort.Sorting;
import org.briarheart.algorithm.util.Preconditions;

import java.util.Arrays;

/**
 * @author Roman Chigvintsev
 */
public class LongMergeSortAlgorithm implements LongSortAlgorithm {
    private static final int INSERTION_SORT_THRESHOLD = 7;

    private final LongSortAlgorithm insertionSort = Sorting.insertionLong();

    @Override
    public void sort(long[] a, int from, int to) {
        Preconditions.notNull(a, "Array must not be null");

        long[] aux = Arrays.copyOf(a, a.length);
        doSort(aux, a, from, to - 1);
    }

    private void doSort(long[] src, long[] dst, int lo, int hi) {
        if (lo + INSERTION_SORT_THRESHOLD > hi) {
            insertionSort.sort(dst, lo, hi + 1);
            return;
        }

        int mid = lo + (hi - lo) / 2;
        doSort(dst, src, lo, mid);
        doSort(dst, src, mid + 1, hi);

        if (src[mid + 1] >= src[mid]) {
            System.arraycopy(src, lo, dst, lo, hi - lo + 1);
            return;
        }

        merge(src, dst, lo, mid, hi);
    }

    static void merge(long[] src, long[] dst, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                dst[k] = src[j++];
            } else if (j > hi) {
                dst[k] = src[i++];
            } else if (src[j] < src[i]) {
                dst[k] = src[j++];
            } else {
                dst[k] = src[i++];
            }
        }
    }
}
