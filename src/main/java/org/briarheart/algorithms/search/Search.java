package org.briarheart.algorithms.search;

import org.briarheart.algorithms.search.impl.AbstractSearchEngine;

/**
 * @author Roman Chigvintsev
 */
public class Search {
    private Search() {
        //no instance
    }

    public static <T extends Comparable<? super T>> int doSearch(T key, T[] a) {
        return doSearch(key, a, SearchingAlgorithm.BINARY);
    }

    public static <T extends Comparable<? super T>> int doSearch(T key, T[] a, SearchingAlgorithm alg) {
        return AbstractSearchEngine.<T>create(alg).search(key, a);
    }
}
