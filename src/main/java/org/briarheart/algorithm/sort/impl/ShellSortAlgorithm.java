package org.briarheart.algorithm.sort.impl;

import org.briarheart.algorithm.sort.SortAlgorithm;

import static org.briarheart.algorithm.util.Comparisons.lt;
import static org.briarheart.algorithm.util.Misc.swap;

/**
 * An implementation of Shell sort algorithm with Knuth's increment sequence (1, 4, 13, 40, ...)
 * <p>
 * Donald Shell published the first version of this sort in 1959.
 * <p>
 * This algorithm has O(<i>n</i><sup>3/2</sup>) time complexity in the worst case and O(<i>n</i>log<i>n</i>)
 * in the best case.
 *
 * @author Roman Chigvintsev
 */
public class ShellSortAlgorithm<T extends Comparable<? super T>> implements SortAlgorithm<T> {
    @Override
    public void sort(T[] a, int from, int to) {
        int step = getStep(to - from);
        while (step > 0) {
            for (int i = from + step; i < to; i++) {
                for (int j = i; j >= from + step && lt(a[j], a[j - step]); j -= step) {
                    swap(a, j, j - step);
                }
            }
            step /= 3;
        }
    }

    static int getStep(int size) {
        // 3x + 1 increment sequence: 1, 4, 13, 40, 121, 364, 1093, ...
        int step = 1;
        while (step < size / 3) {
            step = step * 3 + 1;
        }
        return step;
    }
}
