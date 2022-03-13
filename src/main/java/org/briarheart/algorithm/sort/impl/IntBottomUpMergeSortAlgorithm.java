package org.briarheart.algorithm.sort.impl;

import org.briarheart.algorithm.sort.IntSortAlgorithm;
import org.briarheart.algorithm.util.Preconditions;

import static org.briarheart.algorithm.sort.impl.IntTopDownMergeSortAlgorithm.merge;

/**
 * @author Roman Chigvintsev
 */
public class IntBottomUpMergeSortAlgorithm implements IntSortAlgorithm {
    private int[] aux;

    @Override
    public void sort(int[] a, int from, int to) {
        Preconditions.notNull(a, "Array must not be null");
        aux = new int[a.length];
        doSort(a, from, to - 1);
    }

    private void doSort(int[] a, int lo, int hi) {
        for (int subLen = 1; subLen < hi - lo + 1; subLen += subLen) {
            for (int subLo = lo; subLo <= hi - subLen; subLo += 2 * subLen) {
                int subHi = Math.min(subLo + 2 * subLen - 1, hi);
                System.arraycopy(a, subLo, aux, subLo, subHi - subLo + 1);
                merge(aux, a, subLo, subLo + subLen - 1, subHi);
            }
        }
    }
}
