package org.briarheart.algorithm.sort.impl;

import org.briarheart.algorithm.sort.DoubleSortAlgorithm;

import static org.briarheart.algorithm.util.Comparisons.lt;
import static org.briarheart.algorithm.util.Misc.swap;

/**
 * @author Roman Chigvintsev
 */
public class DoubleHeapSortAlgorithm implements DoubleSortAlgorithm {
    @Override
    public void sort(double[] a, int from, int to) {
        int length = to - from;

        for (int i = length / 2; i >= 1; i--) {
            sink(a, i, from, length);
        }

        while (length > 0) {
            swap(a, from, from + length - 1);
            sink(a, 1, from, --length);
        }
    }

    private void sink(double[] a, int i, int from, int length) {
        while (i * 2 <= length) {
            int j = i * 2;
            if (j < length && lt(a[from + j - 1], a[from + j])) {
                j++;
            }
            if (!lt(a[from + i - 1], a[from + j - 1])) {
                break;
            }
            swap(a, from + i - 1, from + j - 1);
            i = j;
        }
    }
}
