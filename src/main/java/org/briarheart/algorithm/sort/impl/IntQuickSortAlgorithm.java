package org.briarheart.algorithm.sort.impl;

import org.briarheart.algorithm.sort.IntSortAlgorithm;
import org.briarheart.algorithm.util.Preconditions;

import java.util.Random;

import static org.briarheart.algorithm.util.Misc.swap;

/**
 * @author Roman Chigvintsev
 */
public class IntQuickSortAlgorithm implements IntSortAlgorithm {
    private final Random random = new Random();

    @Override
    public void sort(int[] a, int from, int to) {
        Preconditions.notNull(a, "Array must not be null");
        shuffle(a, from, to - 1);
        doSort(a, from, to - 1);
    }

    private void doSort(int[] a, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int j = partition(a, lo, hi);
        doSort(a, lo, j - 1);
        doSort(a, j + 1, hi);
    }

    private int partition(int[] a, int lo, int hi) {
        int v = a[lo];
        int i = lo;
        int j = hi + 1;
        while (true) {
            while (a[++i] < v) {
                if (i == hi) {
                    break;
                }
            }
            while (v < a[--j]) {
                if (j == lo) {
                    break;
                }
            }
            if (i >= j) {
                break;
            }
            swap(a, i, j);
        }
        swap(a, lo, j);
        return j;
    }

    private void shuffle(int[] a, int lo, int hi) {
        for (int i = lo; i < hi; i++) {
            int r = i + random.nextInt(hi - i + 1);
            swap(a, i, r);
        }
    }
}
