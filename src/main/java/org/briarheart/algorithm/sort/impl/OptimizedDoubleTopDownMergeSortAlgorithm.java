package org.briarheart.algorithm.sort.impl;

import org.briarheart.algorithm.sort.DoubleSortAlgorithm;
import org.briarheart.algorithm.sort.Sorting;
import org.briarheart.algorithm.util.Preconditions;

import java.util.Arrays;

import static org.briarheart.algorithm.sort.impl.DoubleTopDownMergeSortAlgorithm.merge;

/**
 * @author Roman Chigvintsev
 */
public class OptimizedDoubleTopDownMergeSortAlgorithm implements DoubleSortAlgorithm {
    private static final int INSERTION_SORT_THRESHOLD = 7;

    private final DoubleSortAlgorithm insertionSort = Sorting.insertionDouble();

    @Override
    public void sort(double[] a, int from, int to) {
        Preconditions.notNull(a, "Array must not be null");

        double[] aux = Arrays.copyOf(a, a.length);
        doSort(aux, a, from, to - 1);
    }

    private void doSort(double[] src, double[] dst, int lo, int hi) {
        if (lo + INSERTION_SORT_THRESHOLD > hi) {
            insertionSort.sort(dst, lo, hi + 1);
            return;
        }

        int mid = lo + (hi - lo) / 2;
        doSort(dst, src, lo, mid);
        doSort(dst, src, mid + 1, hi);

        if (Double.compare(src[mid + 1], src[mid]) >= 0) {
            System.arraycopy(src, lo, dst, lo, hi - lo + 1);
            return;
        }

        merge(src, dst, lo, mid, hi);
    }
}
