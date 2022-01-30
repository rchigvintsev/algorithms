package org.briarheart.algorithm.sort.impl;

import static org.junit.jupiter.api.Assertions.assertFalse;

abstract class AbstractIntSortAlgorithmTest {
    void assertSorted(int[] a) {
        assertSorted(a, 0, a.length);
    }

    void assertSorted(int[] a, int from, int to) {
        for (int i = from + 1; i < to; i++) {
            int n = i;
            assertFalse(a[i] < a[i - 1], ()
                    -> "Array is not sorted: a[" + (n - 1) + "] > a[" + n + "] (" + a[n - 1] + " > " + a[n] + ")");
        }
    }
}
