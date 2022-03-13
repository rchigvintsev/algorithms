package org.briarheart.algorithm.sort.impl;

import org.briarheart.algorithm.sort.IntSortAlgorithm;

/**
 * @author Roman Chigvintsev
 */
public class IntTopDownMergeSortAlgorithm implements IntSortAlgorithm {
    private int[] aux;

    @Override
    public void sort(int[] a, int from, int to) {
        aux = new int[a.length];
        doSort(a, from, to - 1);
    }

    private void doSort(int[] a, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int mid = lo + (hi - lo) / 2;
        doSort(a, lo, mid);
        doSort(a, mid + 1, hi);
        System.arraycopy(a, lo, aux, lo, hi - lo + 1);
        merge(aux, a, lo, mid, hi);
    }

    static void merge(int[] src, int[] dst, int lo, int mid, int hi) {
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
