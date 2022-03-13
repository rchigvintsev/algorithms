package org.briarheart.algorithm.sort.impl;

import org.briarheart.algorithm.sort.SortAlgorithm;

import java.lang.reflect.Array;

import static org.briarheart.algorithm.util.Comparisons.lt;

/**
 * An implementation of merge sort algorithm.
 * <p>
 * This algorithm has O(<i>n</i>log<i>n</i>) time complexity in the average case.
 *
 * @author Roman Chigvintsev
 */
public class TopDownMergeSortAlgorithm<T extends Comparable<? super T>> implements SortAlgorithm<T> {
    private T[] aux;

    @SuppressWarnings("unchecked")
    @Override
    public void sort(T[] a, int from, int to) {
        aux = (T[]) Array.newInstance(a.getClass().getComponentType(), a.length);
        doSort(a, from, to - 1);
    }

    private void doSort(T[] a, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int mid = lo + (hi - lo) / 2;
        doSort(a, lo, mid);
        doSort(a, mid + 1, hi);
        System.arraycopy(a, lo, aux, lo, hi - lo + 1);
        merge(aux, a, lo, mid, hi);
    }

    static <T extends Comparable<? super T>> void merge(T[] src, T[] dst, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                dst[k] = src[j++];
            } else if (j > hi) {
                dst[k] = src[i++];
            } else if (lt(src[j], src[i])) {
                dst[k] = src[j++];
            } else {
                dst[k] = src[i++];
            }
        }
    }
}
