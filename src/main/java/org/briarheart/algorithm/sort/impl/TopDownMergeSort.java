package org.briarheart.algorithm.sort.impl;

import java.lang.reflect.Array;

/**
 * This algorithm has O(<i>n</i>log<i>n</i>) time complexity in the average case.
 *
 * @author Roman Chigvintsev
 */
class TopDownMergeSort<T extends Comparable<? super T>> extends AbstractMergeSort<T> {
    private T[] aux;

    @Override
    protected void beforeSort(T[] a, int lo, int hi) {
        //noinspection unchecked
        aux = (T[]) Array.newInstance(a.getClass().getComponentType(), a.length);
    }

    @Override
    protected void doSort(T[] a, int lo, int hi) {
        if (lo >= hi)
            return;
        int mid = lo + (hi - lo) / 2;
        doSort(a, lo, mid);
        doSort(a, mid + 1, hi);
        System.arraycopy(a, lo, aux, lo, hi - lo + 1);
        merge(aux, a, lo, mid, hi);
    }
}
