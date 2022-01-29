package org.briarheart.algorithm.search.impl;

import org.briarheart.algorithm.search.DoubleSearchAlgorithm;
import org.briarheart.algorithm.util.Preconditions;

/**
 * @author Roman Chigvintsev
 */
public class DoubleBinarySearchAlgorithm implements DoubleSearchAlgorithm {
    @Override
    public int findIndex(double key, double[] a) {
        Preconditions.notNull(a, "Array must not be null");

        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int res = Double.compare(key, a[mid]);
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
