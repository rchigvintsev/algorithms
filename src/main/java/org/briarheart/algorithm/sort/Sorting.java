package org.briarheart.algorithm.sort;

import org.briarheart.algorithm.sort.impl.*;

/**
 * @author Roman Chigvintsev
 */
public class Sorting {
    private Sorting() {
        //no instance
    }

    public static <T extends Comparable<? super T>> SortAlgorithm<T> selection() {
        return new SelectionSortAlgorithm<>();
    }

    public static IntSortAlgorithm selectionInt() {
        return new IntSelectionSortAlgorithm();
    }

    public static LongSortAlgorithm selectionLong() {
        return new LongSelectionSortAlgorithm();
    }

    public static DoubleSortAlgorithm selectionDouble() {
        return new DoubleSelectionSortAlgorithm();
    }

    public static <T extends Comparable<? super T>> SortAlgorithm<T> insertion() {
        return new InsertionSortAlgorithm<>();
    }

    public static IntSortAlgorithm insertionInt() {
        return new IntInsertionSortAlgorithm();
    }

    public static LongSortAlgorithm insertionLong() {
        return new LongInsertionSortAlgorithm();
    }

    public static DoubleSortAlgorithm insertionDouble() {
        return new DoubleInsertionSortAlgorithm();
    }

    public static <T extends Comparable<? super T>> SortAlgorithm<T> shell() {
        return new ShellSortAlgorithm<>();
    }

    public static IntSortAlgorithm shellInt() {
        return new IntShellSortAlgorithm();
    }

    public static LongSortAlgorithm shellLong() {
        return new LongShellSortAlgorithm();
    }

    public static DoubleSortAlgorithm shellDouble() {
        return new DoubleShellSortAlgorithm();
    }

    public static <T extends Comparable<? super T>> T[] doSort(T[] a, SortAlgorithmType alg) {
        if (a == null)
            throw new IllegalArgumentException("Array cannot be null");
        return doSort(a, 0, a.length - 1, alg);
    }

    public static <T extends Comparable<? super T>> SortAlgorithm<T> merge() {
        return new MergeSortAlgorithm<>();
    }

    public static IntSortAlgorithm mergeInt() {
        return new IntMergeSortAlgorithm();
    }

    public static LongSortAlgorithm mergeLong() {
        return new LongMergeSortAlgorithm();
    }

    public static DoubleSortAlgorithm mergeDouble() {
        return new DoubleMergeSortAlgorithm();
    }

    public static <T extends Comparable<? super T>> SortAlgorithm<T> bottomUpMerge() {
        return new BottomUpMergeSortAlgorithm<>();
    }

    public static IntSortAlgorithm bottomUpMergeInt() {
        return new IntBottomUpMergeSortAlgorithm();
    }

    public static LongSortAlgorithm bottomUpMergeLong() {
        return new LongBottomUpMergeSortAlgorithm();
    }

    public static DoubleSortAlgorithm bottomUpMergeDouble() {
        return new DoubleBottomUpMergeSortAlgorithm();
    }

    public static <T extends Comparable<? super T>> T[] doSort(T[] a, int lo, int hi, SortAlgorithmType alg) {
        return AbstractSortEngine.<T>create(alg).sort(a, lo, hi);
    }
}
