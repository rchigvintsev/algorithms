package org.briarheart.algorithm.search.impl;

import org.briarheart.algorithm.search.LongSearchAlgorithm;
import org.briarheart.algorithm.util.Preconditions;

/**
 * @author Roman Chigvintsev
 */
public class LongBinarySearchAlgorithm implements LongSearchAlgorithm {
    @Override
    public int findIndex(long key, long[] a) {
        Preconditions.notNull(a, "Array must not be null");

        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid]) {
                hi = mid - 1;
            } else if (key > a[mid]) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
