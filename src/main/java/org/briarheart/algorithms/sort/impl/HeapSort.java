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
        int n = length - 1;

        for (int i = length / 2; i >= 0; i--)
            sink(a, i, n, lo);

        while (n > 0) {
            exchange(a, lo, lo + n);
            sink(a, 0, --n, lo);
        }
    }

    private void sink(T[] a, int i, int n, int offset) {
        while (2 * i <= n) {
            int j = 2 * i;
            if (j < n && less(a[j + offset], a[j + offset + 1]))
                j++;
            if (!less(a[i + offset], a[j + offset]))
                break;
            exchange(a, i + offset, j + offset);
            i = j;
        }
    }
}
