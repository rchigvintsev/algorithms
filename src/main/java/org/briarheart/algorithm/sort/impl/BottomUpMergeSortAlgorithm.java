package org.briarheart.algorithm.sort.impl;

import org.briarheart.algorithm.sort.SortAlgorithm;
import org.briarheart.algorithm.util.Preconditions;

import java.lang.reflect.Array;

import static org.briarheart.algorithm.sort.impl.TopDownMergeSortAlgorithm.merge;

/**
 * An implementation of bottom-up merge sort algorithm.
 * <p>
 * This algorithm has O(<i>n</i>log<i>n</i>) time complexity in the average case.
 *
 * @author Roman Chigvintsev
 */
public class BottomUpMergeSortAlgorithm<T extends Comparable<? super T>> implements SortAlgorithm<T> {
    private T[] aux;

    @SuppressWarnings("unchecked")
    @Override
    public void sort(T[] a, int from, int to) {
        Preconditions.notNull(a, "Array must not be null");
        aux = (T[]) Array.newInstance(a.getClass().getComponentType(), a.length);
        doSort(a, from, to - 1);
    }

    private void doSort(T[] a, int lo, int hi) {
        for (int subLen = 1; subLen < hi - lo + 1; subLen += subLen) {
            for (int subLo = lo; subLo <= hi - subLen; subLo += 2 * subLen) {
                int subHi = Math.min(subLo + 2 * subLen - 1, hi);
                System.arraycopy(a, subLo, aux, subLo, subHi - subLo + 1);
                merge(aux, a, subLo, subLo + subLen - 1, subHi);
            }
        }
    }
}
