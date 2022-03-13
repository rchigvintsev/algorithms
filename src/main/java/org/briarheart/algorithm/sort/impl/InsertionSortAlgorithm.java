package org.briarheart.algorithm.sort.impl;

import org.briarheart.algorithm.sort.SortAlgorithm;

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
 *
 * @author Roman Chigvintsev
 */
public class InsertionSortAlgorithm<T extends Comparable<? super T>> implements SortAlgorithm<T> {
    @Override
    public void sort(T[] a, int from, int to) {
        for (int i = from + 1; i < to; i++) {
            for (int j = i; j > from && lt(a[j], a[j - 1]); j--) {
                swap(a, j, j - 1);
            }
        }
    }
}
