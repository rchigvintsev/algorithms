package org.briarheart.algorithm.sort;

import org.briarheart.algorithm.util.Preconditions;

/**
 * Represents algorithm to sort array of elements of type {@link T} in ascending order.
 *
 * @author Roman Chigvintsev
 */
public interface SortAlgorithm<T extends Comparable<? super T>> {
    /**
     * Sorts the given array in ascending order.
     *
     * @param a array to sort (must not be {@code null})
     */
    default void sort(T[] a) {
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
    void sort(T[] a, int from, int to);
}
