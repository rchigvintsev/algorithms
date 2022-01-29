package org.briarheart.algorithm.search.impl;

import org.briarheart.algorithm.search.SearchAlgorithm;
import org.briarheart.algorithm.util.Preconditions;

/**
 * @author Roman Chigvintsev
 */
public class BinarySearchAlgorithm<T extends Comparable<? super T>> implements SearchAlgorithm<T> {
    @Override
    public int findIndex(T key, T[] a) {
        Preconditions.notNull(key, "Key must not be null");
        Preconditions.notNull(a, "Array must not be null");

        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int r = key.compareTo(a[mid]);
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
