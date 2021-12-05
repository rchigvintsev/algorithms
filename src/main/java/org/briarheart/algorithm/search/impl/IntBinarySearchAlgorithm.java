package org.briarheart.algorithm.search.impl;

import org.briarheart.algorithm.search.IntSearchingAlgorithm;
import org.briarheart.algorithm.util.Preconditions;

public class IntBinarySearchAlgorithm implements IntSearchingAlgorithm {
    @Override
    public int findIndex(int key, int[] array) {
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
