package org.briarheart.algorithm.search;

/**
 * Represents algorithm to search an index of key in array.
 *
 * @author Roman Chigvintsev
 */
public interface SearchAlgorithm<T extends Comparable<? super T>> {
    /**
     * Searches the given key in the given array.
     *
     * @param key key to search (must not be {@code null})
     * @param a array of values (must not be {@code null})
     * @return index of the given key in array or {@code -1} if array does not contain the key
     */
    int findIndex(T key, T[] a);
}
