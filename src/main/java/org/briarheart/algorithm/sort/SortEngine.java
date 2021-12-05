package org.briarheart.algorithm.sort;

/**
 * @author Roman Chigvintsev
 */
public interface SortEngine<T extends Comparable<? super T>> {
    T[] sort(T[] a);

    T[] sort(T[] a, int lo, int hi);
}
