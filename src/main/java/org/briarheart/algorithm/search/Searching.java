package org.briarheart.algorithm.search;

import org.briarheart.algorithm.search.impl.BinarySearchAlgorithm;
import org.briarheart.algorithm.search.impl.IntBinarySearchAlgorithm;
import org.briarheart.algorithm.search.impl.LongBinarySearchAlgorithm;

/**
 * @author Roman Chigvintsev
 */
public class Searching {
    private Searching() {
        //no instance
    }

    public static <T extends Comparable<? super T>> SearchingAlgorithm<T> binarySearch() {
        return new BinarySearchAlgorithm<>();
    }

    public static IntSearchingAlgorithm binarySearchInt() {
        return new IntBinarySearchAlgorithm();
    }

    public static LongSearchingAlgorithm binarySearchLong() {
        return new LongBinarySearchAlgorithm();
    }
}
