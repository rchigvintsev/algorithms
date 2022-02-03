package org.briarheart.algorithm.sort.impl;

import org.briarheart.algorithm.sort.LongSortAlgorithm;

import static org.briarheart.algorithm.util.Misc.swap;

/**
 * @author Roman Chigvintsev
 */
public class LongInsertionSortAlgorithm implements LongSortAlgorithm {
    @Override
    public void sort(long[] a, int from, int to) {
        int exchanges = 0;
        for (int i = to - 1; i > from; i--) {
            if (a[i] < a[i - 1]) {
                swap(a, i, i - 1);
                exchanges++;
            }
        }

        if (exchanges == 0) {
            return;
        }

        for (int i = from + 2; i < to; i++) {
            long v = a[i];
            int j = i;
            while (v < a[j - 1]) {
                a[j] = a[j - 1];
                j--;
            }
            a[j] = v;
        }
    }
}
