package org.briarheart.algorithms.sort.impl;

/**
 * Heapsort was invented by J.W.J. Williams in 1964.
 * <p>
 * This algorithm has O(<i>n</i>log<i>n</i>) time complexity in the worst, average and the best cases.
 *
 * @author Roman Chigvintsev
 */
class HeapSort<T extends Comparable<? super T>> extends AbstractSortEngine<T> {
    @Override
    protected void doSort(T[] a, int lo, int hi) {
        int length = hi - lo + 1;

        for (int i = length / 2; i >= 1; i--)
            sink(a, i, length, lo);

        while (length > 0) {
            exchange(a, lo + 1, lo + length);
            sink(a, 1, --length, lo);
        }
    }

    private void sink(T[] a, int i, int n, int offset) {
        while (2 * i <= n) {
            int j = 2 * i;
            if (j < n && less(a, j + offset, j + offset + 1))
                j++;
            if (!less(a, i + offset, j + offset))
                break;
            exchange(a, i + offset, j + offset);
            i = j;
        }
    }

    @Override
    protected void exchange(T[] a, int i, int j) {
        super.exchange(a, i - 1, j - 1);
    }

    private boolean less(T[] a, int i, int j) {
        return a[i - 1].compareTo(a[j - 1]) < 0;
    }
}
