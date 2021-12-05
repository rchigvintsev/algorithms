package org.briarheart.algorithm.search.impl;

import org.briarheart.algorithm.search.DoubleSearchingAlgorithm;
import org.briarheart.algorithm.util.Preconditions;

public class DoubleBinarySearchAlgorithm implements DoubleSearchingAlgorithm {
    @Override
    public int findIndex(double key, double[] array) {
        Preconditions.notNull(array, "Array must not be null");

        int lo = 0;
        int hi = array.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int res = Double.compare(key, array[mid]);
            if (res < 0) {
                hi = mid - 1;
            } else if (res > 0) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
