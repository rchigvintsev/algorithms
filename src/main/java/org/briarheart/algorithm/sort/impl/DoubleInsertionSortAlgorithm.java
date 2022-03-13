package org.briarheart.algorithm.sort.impl;

import org.briarheart.algorithm.sort.DoubleSortAlgorithm;

import static org.briarheart.algorithm.util.Misc.swap;

/**
 * @author Roman Chigvintsev
 */
public class DoubleInsertionSortAlgorithm implements DoubleSortAlgorithm {
    @Override
    public void sort(double[] a, int from, int to) {
        for (int i = from + 1; i < to; i++) {
            for (int j = i; j > from && Double.compare(a[j], a[j - 1]) < 0; j--) {
                swap(a, j, j - 1);
            }
        }
    }
}
