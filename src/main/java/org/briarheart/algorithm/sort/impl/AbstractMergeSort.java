package org.briarheart.algorithm.sort.impl;

/**
 * @author Roman Chigvintsev
 */
abstract class AbstractMergeSort<T extends Comparable<? super T>> extends AbstractSortEngine<T> {
    public void merge(T[] src, T[] dst, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid)
                dst[k] = src[j++];
            else if (j > hi)
                dst[k] = src[i++];
            else if (less(src[j], src[i]))
                dst[k] = src[j++];
            else
                dst[k] = src[i++];
        }
    }
}
