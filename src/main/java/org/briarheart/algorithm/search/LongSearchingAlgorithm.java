package org.briarheart.algorithm.search;

/**
 * Specialization of {@link SearchingAlgorithm} to search an index of key in array of {@code long}s.
 */
public interface LongSearchingAlgorithm {
    /**
     * Searches the given key in the given array.
     *
     * @param key key to search
     * @param array array of values (must not be {@code null})
     * @return index of the given key in array or {@code -1} if array does not contain the key
     */
    int findIndex(long key, long[] array);
}
