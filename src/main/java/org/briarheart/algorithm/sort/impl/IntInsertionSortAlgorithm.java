package org.briarheart.algorithm.sort.impl;

import org.briarheart.algorithm.sort.IntSortAlgorithm;

import static org.briarheart.algorithm.util.Misc.swap;

/**
 * @author Roman Chigvintsev
 */
public class IntInsertionSortAlgorithm implements IntSortAlgorithm {
    @Override
    public void sort(int[] a, int from, int to) {
        for (int i = from + 1; i < to; i++) {
            for (int j = i; j > from && a[j] < a[j - 1]; j--) {
                swap(a, j, j - 1);
            }
        }
    }
}
