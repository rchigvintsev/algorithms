package org.briarheart.algorithms.search.impl;

import org.briarheart.algorithms.search.SearchEngine;
import org.briarheart.algorithms.search.SearchingAlgorithm;

/**
 * @author Roman Chigvintsev
 */
public abstract class AbstractSearchEngine<T extends Comparable<? super T>> implements SearchEngine<T> {
    public static <T extends Comparable<? super T>> SearchEngine<T> create(SearchingAlgorithm alg) {
        if (alg == null)
            alg = SearchingAlgorithm.BINARY;
        switch (alg) {
            case BINARY:
                return new BinarySearch<>();
        }
        throw new IllegalArgumentException("Unknown algorithm: " + alg);
    }

    @Override
    public int search(T key, T[] a) {
        if (a == null)
            throw new IllegalArgumentException("Array cannot be null");
        return doSearch(key, a);
    }

    protected abstract int doSearch(T key, T[] a);
}
