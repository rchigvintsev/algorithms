package org.briarheart.algorithm.search.impl;

import org.briarheart.algorithm.search.IntSearchAlgorithm;
import org.briarheart.algorithm.util.Preconditions;

/**
 * @author Roman Chigvintsev
 */
public class IntBinarySearchAlgorithm implements IntSearchAlgorithm {
    @Override
    public int findIndex(int key, int[] a) {
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
