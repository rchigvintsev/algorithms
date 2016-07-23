package org.briarheart.algorithms.search;

/**
 * @author Roman Chigvintsev
 */
public interface SearchEngine {
    int doSearch(int key, int[] a);

    <T extends Comparable<? super T>> int doSearch(T key, T[] a);
}
