package org.briarheart.algorithm.search.impl;

import org.briarheart.algorithm.search.LongSearchingAlgorithm;
import org.briarheart.algorithm.util.Preconditions;

public class LongBinarySearchAlgorithm implements LongSearchingAlgorithm {
    @Override
    public int findIndex(long key, long[] array) {
        Preconditions.notNull(array, "Array must not be null");

        int lo = 0;
        int hi = array.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (key < array[mid]) {
                hi = mid - 1;
            } else if (key > array[mid]) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
