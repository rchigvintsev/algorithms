package org.briarheart.algorithms.search;

/**
 * @author Roman Chigvintsev
 */
public interface SearchEngine<T extends Comparable<? super T>> {
    int search(T key, T[] a);
}
