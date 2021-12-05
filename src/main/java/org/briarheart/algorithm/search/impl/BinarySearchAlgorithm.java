package org.briarheart.algorithm.search.impl;

import org.briarheart.algorithm.search.SearchingAlgorithm;
import org.briarheart.algorithm.util.Preconditions;

/**
 * @author Roman Chigvintsev
 */
public class BinarySearchAlgorithm<T extends Comparable<? super T>> implements SearchingAlgorithm<T> {
    @Override
    public int findIndex(T key, T[] array) {
        Preconditions.notNull(key, "Key must not be null");
        Preconditions.notNull(array, "Array must not be null");

        int lo = 0;
        int hi = array.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int r = key.compareTo(array[mid]);
            if (r < 0) {
                hi = mid - 1;
            } else if (r > 0) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
