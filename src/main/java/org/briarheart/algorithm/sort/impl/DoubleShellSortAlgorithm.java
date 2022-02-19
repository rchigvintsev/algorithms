package org.briarheart.algorithm.sort.impl;

import org.briarheart.algorithm.sort.DoubleSortAlgorithm;
import org.briarheart.algorithm.util.Preconditions;

import static org.briarheart.algorithm.sort.impl.ShellSortAlgorithm.getStep;
import static org.briarheart.algorithm.util.Misc.swap;

/**
 * @author Roman Chigvintsev
 */
public class DoubleShellSortAlgorithm implements DoubleSortAlgorithm {
    @Override
    public void sort(double[] a, int from, int to) {
        Preconditions.notNull(a, "Array must not be null");

        int step = getStep(to - from);
        while (step > 0) {
            for (int i = from + step; i < to; i++) {
                for (int j = i; j >= from + step && Double.compare(a[j], a[j - step]) < 0; j -= step) {
                    swap(a, j, j - step);
                }
            }
            step /= 3;
        }
    }
}
