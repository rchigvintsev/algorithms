package org.briarheart.algorithm.sort.impl;

import org.briarheart.algorithm.sort.IntSortAlgorithm;
import org.briarheart.algorithm.util.TestFiles;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class IntQuickSortAlgorithmTest extends AbstractIntSortAlgorithmTest {
    private static final String TEST_DATA_FILE_NAME = "tinyW.txt";

    private static IntSortAlgorithm algorithm;
    private static int[] testData;

    @BeforeAll
    static void beforeAll() throws IOException {
        algorithm = new IntQuickSortAlgorithm();
        testData = TestFiles.readAllInts(TEST_DATA_FILE_NAME);
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
}