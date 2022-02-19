package org.briarheart.algorithm.sort.impl;

import org.briarheart.algorithm.sort.SortAlgorithm;
import org.briarheart.algorithm.util.Preconditions;

import static org.briarheart.algorithm.util.Comparisons.lt;
import static org.briarheart.algorithm.util.Misc.swap;

/**
 * <blockquote>
 * <p>
 * Insertion sort is a simple sorting algorithm that builds the final sorted array (or list) one item at a time.
 * It is much less efficient on large lists than more advanced algorithms such as quicksort, heapsort, or merge sort.
 * </p>
 * <footer>
 * <cite>Wikipedia</cite>
 * </footer>
 * </blockquote>
 * <p>
 * Insertion sort algorithm has O(n<sup>2</sup>) time complexity in the average case.
 * <p>
 * This is an optimized version of insertion sort algorithm with half exchanges and a sentinel.
 *
 * @author Roman Chigvintsev
 */
public class InsertionSortAlgorithm<T extends Comparable<? super T>> implements SortAlgorithm<T> {
    @Override
    public void sort(T[] a, int from, int to) {
        Preconditions.notNull(a, "Array must not be null");

        int exchanges = 0;
        for (int i = to - 1; i > from; i--) {
            if (lt(a[i], a[i - 1])) {
                swap(a, i, i - 1);
                exchanges++;
            }
        }

        if (exchanges == 0) {
            return;
        }

        for (int i = from + 2; i < to; i++) {
            T v = a[i];
            int j = i;
            while (lt(v, a[j - 1])) {
                a[j] = a[j - 1];
                j--;
            }
            a[j] = v;
        }
    }
}
