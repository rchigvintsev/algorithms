package org.briarheart.algorithm.sort.impl;

import org.briarheart.algorithm.sort.IntSortAlgorithm;
import org.briarheart.algorithm.util.Preconditions;

import static org.briarheart.algorithm.util.Misc.swap;

/**
 * @author Roman Chigvintsev
 */
public class IntSelectionSortAlgorithm implements IntSortAlgorithm {
    @Override
    public void sort(int[] a, int from, int to) {
        Preconditions.notNull(a, "Array must not be null");

        for (int i = from; i < to - 1; i++) {
            int min = i;
            for (int j = i + 1; j < to; j++) {
                if (a[j] < a[min]) {
                    min = j;
                }
            }
            swap(a, i, min);
        }
    }
}
