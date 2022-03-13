package org.briarheart.algorithm.sort.impl;

import org.briarheart.algorithm.sort.DoubleSortAlgorithm;

/**
 * @author Roman Chigvintsev
 */
public class DoubleTopDownMergeSortAlgorithm implements DoubleSortAlgorithm {
    private double[] aux;

    @Override
    public void sort(double[] a, int from, int to) {
        aux = new double[a.length];
        doSort(a, from, to - 1);
    }

    private void doSort(double[] a, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int mid = lo + (hi - lo) / 2;
        doSort(a, lo, mid);
        doSort(a, mid + 1, hi);
        System.arraycopy(a, lo, aux, lo, hi - lo + 1);
        merge(aux, a, lo, mid, hi);
    }

    static void merge(double[] src, double[] dst, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                dst[k] = src[j++];
            } else if (j > hi) {
                dst[k] = src[i++];
            } else if (Double.compare(src[j], src[i]) < 0) {
                dst[k] = src[j++];
            } else {
                dst[k] = src[i++];
            }
        }
    }
}
