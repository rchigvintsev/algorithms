package org.briarheart.algorithm.sort.impl;

import org.briarheart.algorithm.sort.SortAlgorithm;
import org.briarheart.algorithm.sort.Sorting;
import org.briarheart.algorithm.util.TestFiles;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class SelectionSortAlgorithmTest {
    private static final String TEST_DATA_FILE_NAME = "tiny.txt";

    private static SortAlgorithm<String> algorithm;
    private static String[] testData;

    @BeforeAll
    static void beforeAll() throws IOException {
        algorithm = Sorting.selection();
        testData = TestFiles.readAllWords(TEST_DATA_FILE_NAME);
    }

    @Test
    void shouldThrowExceptionOnSortWhenArrayIsNull() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> algorithm.sort(null));
        assertEquals("Array must not be null", e.getMessage());
    }

    @Test
    void shouldSort() {
        algorithm.sort(testData);
        assertSorted(testData);
    }

    @Test
    void shouldSortRange() {
        int subarrayLength = testData.length / 2;
        int from = (testData.length - subarrayLength) / 2;
        int to = from + subarrayLength;
        algorithm.sort(testData, from, to);
        assertSorted(testData, from, to);
    }

    private void assertSorted(String[] a) {
        assertSorted(a, 0, a.length);
    }

    private void assertSorted(String[] a, int from, int to) {
        for (int i = from + 1; i < to; i++) {
            int n = i;
            assertFalse(a[i].compareTo(a[i - 1]) < 0, ()
                    -> "Array is not sorted: a[" + (n - 1) + "] > a[" + n + "] (" + a[n - 1] + " > " + a[n] + ")");
        }
    }
}