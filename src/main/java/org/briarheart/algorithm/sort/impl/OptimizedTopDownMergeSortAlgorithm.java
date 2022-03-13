package org.briarheart.algorithm.sort.impl;

import org.briarheart.algorithm.sort.SortAlgorithm;
import org.briarheart.algorithm.sort.Sorting;
import org.briarheart.algorithm.util.Preconditions;

import java.util.Arrays;

import static org.briarheart.algorithm.sort.impl.TopDownMergeSortAlgorithm.merge;
import static org.briarheart.algorithm.util.Comparisons.lt;

/**
 * An implementation of optimized merge sort algorithm.
 * <p>
 * This algorithm has O(<i>n</i>log<i>n</i>) time complexity in the average case.
 *
 * @author Roman Chigvintsev
 */
public class OptimizedTopDownMergeSortAlgorithm<T extends Comparable<? super T>> implements SortAlgorithm<T> {
    private static final int INSERTION_SORT_THRESHOLD = 7;

    private final SortAlgorithm<T> insertionSort = Sorting.insertion();

    @Override
    public void sort(T[] a, int from, int to) {
        Preconditions.notNull(a, "Array must not be null");
        T[] aux = Arrays.copyOf(a, a.length);
        doSort(aux, a, from, to - 1);
    }

    private void doSort(T[] src, T[] dst, int lo, int hi) {
        if (lo + INSERTION_SORT_THRESHOLD > hi) {
            insertionSort.sort(dst, lo, hi + 1);
            return;
        }

        int mid = lo + (hi - lo) / 2;
        doSort(dst, src, lo, mid);
        doSort(dst, src, mid + 1, hi);

        if (!lt(src[mid + 1], src[mid])) {
            System.arraycopy(src, lo, dst, lo, hi - lo + 1);
            return;
        }

        merge(src, dst, lo, mid, hi);
    }
}
