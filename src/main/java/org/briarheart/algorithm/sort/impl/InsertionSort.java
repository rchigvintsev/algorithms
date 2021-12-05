package org.briarheart.algorithm.sort.impl;

/**
 * Current implementation makes ~ 1/2 n^2 compares and exchanges in the worst case, so it is not suitable for
 * sorting large arbitrary arrays. More precisely, the number of exchanges is exactly equal to the number of
 * inversions. So, for example, it sorts a partially-sorted array in linear time.
 *
 * <p> The sorting algorithm is stable and uses O(1) extra memory.
 *
 * <p> This algorithm has O(n<sup>2</sup>) time complexity in the average case.
 *
 * @author Roman Chigvintsev
 */
class InsertionSort<T extends Comparable<? super T>> extends AbstractSortEngine<T> {
    @Override
    protected void doSort(T[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            for (int j = i; j > lo && less(a[j], a[j - 1]); j--)
                exchange(a, j, j - 1);
    }
}
