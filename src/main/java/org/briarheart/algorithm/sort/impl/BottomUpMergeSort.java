package org.briarheart.algorithm.sort.impl;

import java.lang.reflect.Array;

/**
 * @author Roman Chigvintsev
 */
class BottomUpMergeSort<T extends Comparable<? super T>> extends AbstractMergeSort<T> {
    private T[] aux;

    @Override
    protected void beforeSort(T[] a, int lo, int hi) {
        //noinspection unchecked
        aux = (T[]) Array.newInstance(a.getClass().getComponentType(), a.length);
    }

    @Override
    protected void doSort(T[] a, int lo, int hi) {
        for (int sublen = 1; sublen < hi - lo + 1; sublen += sublen)
            for (int sublo = lo; sublo <= hi - sublen; sublo += 2 * sublen) {
                int subhi = Math.min(sublo + 2 * sublen - 1, hi);
                System.arraycopy(a, sublo, aux, sublo, subhi - sublo + 1);
                merge(aux, a, sublo, sublo + sublen - 1, subhi);
            }
    }
}
