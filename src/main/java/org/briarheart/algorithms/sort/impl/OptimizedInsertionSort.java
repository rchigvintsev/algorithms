package org.briarheart.algorithms.sort.impl;

/**
 * An optimized version of insertion sort algorithm (with half exchanges and a sentinel).
 *
 * @author Roman Chigvintsev
 */
class OptimizedInsertionSort<T extends Comparable<? super T>> extends AbstractSortEngine<T> {
    @Override
    protected void doSort(T[] a, int lo, int hi) {
        int exchanges = 0;
        for (int i = hi; i > lo; i--)
            if (less(a[i], a[i - 1])) {
                exchange(a, i, i - 1);
                exchanges++;
            }

        if (exchanges == 0)
            return;

        for (int i = lo + 2; i <= hi; i++) {
            T v = a[i];
            int j = i;
            while (less(v, a[j - 1])) {
                a[j] = a[j - 1];
                j--;
            }
            a[j] = v;
        }
    }
}
