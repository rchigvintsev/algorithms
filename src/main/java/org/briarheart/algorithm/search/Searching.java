package org.briarheart.algorithm.search;

import org.briarheart.algorithm.search.impl.BinarySearchAlgorithm;
import org.briarheart.algorithm.search.impl.DoubleBinarySearchAlgorithm;
import org.briarheart.algorithm.search.impl.IntBinarySearchAlgorithm;
import org.briarheart.algorithm.search.impl.LongBinarySearchAlgorithm;

/**
 * @author Roman Chigvintsev
 */
public class Searching {
    private Searching() {
        //no instance
    }

    public static <T extends Comparable<? super T>> SearchAlgorithm<T> binary() {
        return new BinarySearchAlgorithm<>();
    }

    public static IntSearchAlgorithm binaryInt() {
        return new IntBinarySearchAlgorithm();
    }

    public static LongSearchAlgorithm binaryLong() {
        return new LongBinarySearchAlgorithm();
    }

    public static DoubleSearchAlgorithm binaryDouble() {
        return new DoubleBinarySearchAlgorithm();
    }
}
