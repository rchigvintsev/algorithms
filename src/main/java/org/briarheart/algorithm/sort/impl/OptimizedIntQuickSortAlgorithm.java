package org.briarheart.algorithm.sort.impl;

import org.briarheart.algorithm.sort.IntSortAlgorithm;
import org.briarheart.algorithm.sort.Sorting;
import org.briarheart.algorithm.util.Preconditions;

import static org.briarheart.algorithm.util.Misc.swap;

/**
 * @author Roman Chigvintsev
 */
public class OptimizedIntQuickSortAlgorithm implements IntSortAlgorithm {
    private static final int INSERTION_SORT_THRESHOLD = 7;

    private final IntSortAlgorithm insertionSort = Sorting.insertionInt();

    @Override
    public void sort(int[] a, int from, int to) {
        Preconditions.notNull(a, "Array must not be null");
        doSort(a, from, to - 1);
    }

    private void doSort(int[] a, int lo, int hi) {
        if (lo + INSERTION_SORT_THRESHOLD > hi) {
            insertionSort.sort(a, lo, hi + 1);
            return;
        }

        int length = hi - lo + 1;
        int mid = lo + length / 2;

        int n = length <= 40 ? median3(a, lo, mid, hi) : tukeyNinther(a, lo, mid, hi);
        swap(a, n, lo);

        // Bentley-McIlroy 3-way partitioning
        int i = lo, j = hi + 1;
        int p = lo, q = hi + 1;
        int v = a[lo];
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
            if (i == j && a[i] == v) {
                swap(a, ++p, i);
            }
            if (i >= j) {
                break;
            }
            swap(a, i, j);
            if (a[i] == v) {
                swap(a, ++p, i);
            }
            if (a[j] == v) {
                swap(a, --q, j);
            }
        }

        i = j + 1;

        for (int k = lo; k <= p; k++) {
            swap(a, k, j--);
        }
        for (int k = hi; k >= q; k--) {
            swap(a, k, i++);
        }

        if (lo < j) {
            doSort(a, lo, j);
        }
        if (hi > i) {
            doSort(a, i, hi);
        }
    }

    private int median3(int[] a, int i, int j, int k) {
        return a[i] < a[j]
                ? (a[j] < a[k] ? j : (a[i] < a[k] ? k : i))
                : (a[k] < a[j] ? j : (a[k] < a[i] ? k : i));
    }

    private int tukeyNinther(int[] a, int lo, int mid, int hi) {
        int length = hi - lo + 1;
        int eps = length / 8;
        int m1 = median3(a, lo, lo + eps, lo + eps + eps);
        int m2 = median3(a, mid - eps, mid, mid + eps);
        int m3 = median3(a, hi - eps - eps, hi - eps, hi);
        return median3(a, m1, m2, m3);
    }
}
