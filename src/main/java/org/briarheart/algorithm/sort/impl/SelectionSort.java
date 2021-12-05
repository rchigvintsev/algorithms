package org.briarheart.algorithm.sort.impl;

/**
 * This algorithm has O(n<sup>2</sup>) time complexity.
 *
 * @author Roman Chigvintsev
 */
class SelectionSort<T extends Comparable<? super T>> extends AbstractSortEngine<T> {
    @Override
    protected void doSort(T[] a, int lo, int hi) {
        for (int i = lo; i < hi; i++) {
            int min = i;
            for (int j = i + 1; j <= hi; j++)
                if (less(a[j], a[min]))
                    min = j;
            exchange(a, i, min);
        }
    }
}
