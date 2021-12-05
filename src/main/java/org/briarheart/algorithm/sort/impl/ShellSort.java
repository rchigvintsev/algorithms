package org.briarheart.algorithm.sort.impl;

/**
 * An implementation of Shell sort algorithm with Knuth's increment sequence (1, 4, 13, 40, ...)
 * <p>
 * Donald Shell published the first version of this sort in 1959.
 * <p>
 * This algorithm has O(<i>n</i><sup>3/2</sup>) time complexity in the worst case
 * and O(<i>n</i>log<i>n</i>) in the best case.
 *
 * @author Roman Chigvintsev
 */
class ShellSort<T extends Comparable<? super T>> extends AbstractSortEngine<T> {
    @Override
    protected void doSort(T[] a, int lo, int hi) {
        // 3x + 1 increment sequence: 1, 4, 13, 40, 121, 364, 1093, ...
        int step = 1;
        while (step < (hi - lo + 1) / 3)
            step = step * 3 + 1;
        while (step > 0) {
            for (int i = lo + step; i <= hi; i++)
                for (int j = i; j >= lo + step && less(a[j], a[j - step]); j -= step)
                    exchange(a, j, j - step);
            step /= 3;
        }
    }
}
