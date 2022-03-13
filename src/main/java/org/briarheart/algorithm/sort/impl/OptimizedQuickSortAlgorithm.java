package org.briarheart.algorithm.sort.impl;

import org.briarheart.algorithm.sort.SortAlgorithm;
import org.briarheart.algorithm.sort.Sorting;
import org.briarheart.algorithm.util.Preconditions;

import static org.briarheart.algorithm.util.Comparisons.eq;
import static org.briarheart.algorithm.util.Comparisons.lt;
import static org.briarheart.algorithm.util.Misc.swap;

/**
 * Quicksort is developed by Tony Hoare in 1959. This is an optimized version of this algorithm.
 * <p>
 * This algorithm has O(<i>n</i><sup>2</sup>) time complexity in the worst case and O(<i>n</i>log<i>n</i>) time
 * complexity in the average case.
 *
 * @author Roman Chigvintsev
 */
public class OptimizedQuickSortAlgorithm<T extends Comparable<? super T>> implements SortAlgorithm<T> {
    private static final int INSERTION_SORT_THRESHOLD = 7;

    private final SortAlgorithm<T> insertionSort = Sorting.insertion();

    @Override
    public void sort(T[] a, int from, int to) {
        Preconditions.notNull(a, "Array must not be null");
        doSort(a, from, to - 1);
    }

    private void doSort(T[] a, int lo, int hi) {
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
        T v = a[lo];
        while (true) {
            while (lt(a[++i], v)) {
                if (i == hi) {
                    break;
                }
            }
            while (lt(v, a[--j])) {
                if (j == lo) {
                    break;
                }
            }
            if (i == j && eq(a[i], v)) {
                swap(a, ++p, i);
            }
            if (i >= j) {
                break;
            }
            swap(a, i, j);
            if (eq(a[i], v)) {
                swap(a, ++p, i);
            }
            if (eq(a[j], v)) {
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

    private int median3(T[] a, int i, int j, int k) {
        return lt(a[i], a[j])
                ? (lt(a[j], a[k]) ? j : (lt(a[i], a[k]) ? k : i))
                : (lt(a[k], a[j]) ? j : (lt(a[k], a[i]) ? k : i));
    }

    private int tukeyNinther(T[] a, int lo, int mid, int hi) {
        int length = hi - lo + 1;
        int eps = length / 8;
        int m1 = median3(a, lo, lo + eps, lo + eps + eps);
        int m2 = median3(a, mid - eps, mid, mid + eps);
        int m3 = median3(a, hi - eps - eps, hi - eps, hi);
        return median3(a, m1, m2, m3);
    }
}
