package org.briarheart.algorithm.sort.impl;

import org.briarheart.algorithm.sort.SortAlgorithm;
import org.briarheart.algorithm.util.Preconditions;

import java.util.Random;

import static org.briarheart.algorithm.util.Comparisons.lt;
import static org.briarheart.algorithm.util.Misc.swap;

/**
 * Quicksort is developed by Tony Hoare in 1959.
 * <p>
 * This algorithm has O(<i>n</i><sup>2</sup>) time complexity in the worst case and O(<i>n</i>log<i>n</i>) time
 * complexity in the average case.
 *
 * @author Roman Chigvintsev
 */
public class QuickSortAlgorithm<T extends Comparable<? super T>> implements SortAlgorithm<T> {
    private final Random random = new Random();

    @Override
    public void sort(T[] a, int from, int to) {
        Preconditions.notNull(a, "Array must not be null");
        shuffle(a, from, to - 1);
        doSort(a, from, to - 1);
    }

    private void doSort(T[] a, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int j = partition(a, lo, hi);
        doSort(a, lo, j - 1);
        doSort(a, j + 1, hi);
    }

    private int partition(T[] a, int lo, int hi) {
        T v = a[lo];
        int i = lo;
        int j = hi + 1;
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
            if (i >= j) {
                break;
            }
            swap(a, i, j);
        }
        swap(a, lo, j);
        return j;
    }

    private void shuffle(T[] a, int lo, int hi) {
        for (int i = lo; i < hi; i++) {
            int r = i + random.nextInt(hi - i + 1);
            swap(a, i, r);
        }
    }
}
