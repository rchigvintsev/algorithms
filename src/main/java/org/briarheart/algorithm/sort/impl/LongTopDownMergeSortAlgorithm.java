package org.briarheart.algorithm.sort.impl;

import org.briarheart.algorithm.sort.LongSortAlgorithm;

/**
 * @author Roman Chigvintsev
 */
public class LongTopDownMergeSortAlgorithm implements LongSortAlgorithm {
    private long[] aux;

    @Override
    public void sort(long[] a, int from, int to) {
        aux = new long[a.length];
        doSort(a, from, to - 1);
    }

    private void doSort(long[] a, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int mid = lo + (hi - lo) / 2;
        doSort(a, lo, mid);
        doSort(a, mid + 1, hi);
        System.arraycopy(a, lo, aux, lo, hi - lo + 1);
        merge(aux, a, lo, mid, hi);
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
