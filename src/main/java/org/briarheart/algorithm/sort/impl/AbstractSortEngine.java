package org.briarheart.algorithm.sort.impl;

import org.briarheart.algorithm.sort.SortAlgorithmType;
import org.briarheart.algorithm.sort.SortEngine;

/**
 * @author Roman Chigvintsev
 */
public abstract class AbstractSortEngine<T extends Comparable<? super T>> implements SortEngine<T> {
    public static <T extends Comparable<? super T>> SortEngine<T> create(SortAlgorithmType alg) {
        switch (alg) {
            case OPTIMIZED_INSERTION:
                return new OptimizedInsertionSort<>();
            case TOP_DOWN_MERGE:
                return new TopDownMergeSort<>();
            case OPTIMIZED_TOP_DOWN_MERGE:
                return new OptimizedTopDownMergeSort<>();
            case BOTTOM_UP_MERGE:
                return new BottomUpMergeSort<>();
            case QUICK:
                return new QuickSort<>();
            case OPTIMIZED_QUICK:
                return new OptimizedQuickSort<>();
            case HEAP:
                return new HeapSort<>();
        }
        throw new IllegalArgumentException("Unknown algorithm: " + alg);
    }

    @Override
    public T[] sort(T[] a) {
        if (!checkArray(a))
            return a;
        return sort(a, 0, a.length - 1);
    }

    @Override
    public T[] sort(T[] a, int lo, int hi) {
        if (!checkArray(a) || !checkBounds(a, lo, hi))
            return a;
        beforeSort(a, lo, hi);
        doSort(a, lo, hi);
        afterSort(a, lo, hi);
        return a;
    }

    protected void beforeSort(T[] a, int lo, int hi) {
        // Override in subclasses
    }

    protected abstract void doSort(T[] a, int lo, int hi);

    protected void afterSort(T[] a, int lo, int hi) {
        // Override in subclasses
    }

    protected boolean less(T a, T b) {
        if (a == null || b == null)
            return a == null && b != null;
        return a.compareTo(b) < 0;
    }

    protected boolean eq(T a, T b) {
        if (a == null || b == null)
            return a == null && b == null;
        return a.compareTo(b) == 0;
    }

    protected void exchange(T[] a, int i, int j) {
        T tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    private boolean checkArray(T[] a) {
        if (a == null)
            throw new IllegalArgumentException("Array cannot be null");
        return a.length >= 2;
    }

    private boolean checkBounds(T[] a, int lo, int hi) {
        if (lo < 0 || lo >= a.length)
            throw new ArrayIndexOutOfBoundsException(lo);
        if (hi < 0 || hi >= a.length)
            throw new ArrayIndexOutOfBoundsException(hi);
        if (lo > hi)
            throw new IllegalArgumentException("Lower bound of array cannot be greater than upper");
        return lo != hi;
    }
}
