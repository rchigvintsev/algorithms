package org.briarheart.algorithm.sort;

import org.briarheart.algorithm.util.Preconditions;

/**
 * Specialization of {@link SortAlgorithm} to sort array of {@code int}s.
 *
 * @author Roman Chigvintsev
 */
public interface IntSortAlgorithm {
    /**
     * Sorts the given array in ascending order.
     *
     * @param a array to sort (must not be {@code null})
     */
    default void sort(int[] a) {
        Preconditions.notNull(a, "Array must not be null");
        sort(a, 0, a.length);
    }

    /**
     * Sorts the specified range of the given array in ascending order.
     *
     * @param a array to sort (must not be {@code null})
     * @param from initial index of range to be sorted, inclusive
     * @param to final index of range to be sorted, exclusive
     */
    void sort(int[] a, int from, int to);
}
