package org.briarheart.algorithm.sort.impl;

import static org.junit.jupiter.api.Assertions.assertFalse;

abstract class AbstractSortAlgorithmTest {
    void assertSorted(String[] a) {
        assertSorted(a, 0, a.length);
    }

    void assertSorted(String[] a, int from, int to) {
        for (int i = from + 1; i < to; i++) {
            int n = i;
            assertFalse(a[i].compareTo(a[i - 1]) < 0, ()
                    -> "Array is not sorted: a[" + (n - 1) + "] > a[" + n + "] (" + a[n - 1] + " > " + a[n] + ")");
        }
    }
}
