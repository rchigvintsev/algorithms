package org.briarheart.algorithm.sort.impl;

import org.briarheart.algorithm.sort.SortAlgorithm;
import org.briarheart.algorithm.util.Preconditions;

import static org.briarheart.algorithm.util.Comparisons.lt;
import static org.briarheart.algorithm.util.Misc.swap;

/**
 * Primitive algorithm with O(n<sup>2</sup>) time complexity.
 *
 * @author Roman Chigvintsev
 */
public class SelectionSortAlgorithm<T extends Comparable<? super T>> implements SortAlgorithm<T> {
    @Override
    public void sort(T[] a, int from, int to) {
        Preconditions.notNull(a, "Array must not be null");

        for (int i = from; i < to - 1; i++) {
            int min = i;
            for (int j = i + 1; j < to; j++) {
                if (lt(a[j], a[min])) {
                    min = j;
                }
            }
            swap(a, i, min);
        }
    }
}
