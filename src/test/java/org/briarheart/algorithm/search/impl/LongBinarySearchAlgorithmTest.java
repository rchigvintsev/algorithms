package org.briarheart.algorithm.search.impl;

import org.briarheart.algorithm.search.LongSearchingAlgorithm;
import org.briarheart.algorithm.search.Searching;
import org.briarheart.algorithm.util.TestFiles;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LongBinarySearchAlgorithmTest {
    private static final String TEST_DATA_FILE_NAME = "largeW.txt";

    private static LongSearchingAlgorithm algorithm;
    private static long[] testData;

    @BeforeAll
    static void beforeAll() throws IOException {
        algorithm = Searching.binarySearchLong();
        testData = TestFiles.readAllLongs(TEST_DATA_FILE_NAME);
        Arrays.sort(testData);
    }

    @Test
    void shouldThrowExceptionOnIndexFindWhenArrayIsNull() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> algorithm.findIndex(0L, null));
        assertEquals("Array must not be null", e.getMessage());
    }

    @Test
    void shouldFindValueIndex() {
        assertEquals(500000, algorithm.findIndex(500451L, testData));
    }

    @Test
    void shouldFindFirstValueIndex() {
        assertEquals(2, algorithm.findIndex(0L, testData));
    }

    @Test
    void shouldFindLastValueIndex() {
        assertEquals(999999, algorithm.findIndex(999999L, testData));
    }

    @Test
    void shouldReturnMinusOneOnIndexFindWhenArrayDoesNotContainValue() {
        assertEquals(-1, algorithm.findIndex(-1L, testData));
    }
}