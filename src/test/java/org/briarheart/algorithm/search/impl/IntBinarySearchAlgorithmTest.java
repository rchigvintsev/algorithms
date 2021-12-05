package org.briarheart.algorithm.search.impl;

import org.briarheart.algorithm.search.IntSearchingAlgorithm;
import org.briarheart.algorithm.search.Searching;
import org.briarheart.algorithm.util.TestFiles;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class IntBinarySearchAlgorithmTest {
    private static final String TEST_DATA_FILE_NAME = "largeW.txt";

    private static IntSearchingAlgorithm algorithm;
    private static int[] testData;

    @BeforeAll
    static void beforeAll() throws IOException {
        algorithm = Searching.binarySearchInt();
        testData = TestFiles.readAllInts(TEST_DATA_FILE_NAME);
        Arrays.sort(testData);
    }

    @Test
    void shouldThrowExceptionOnIndexFindWhenArrayIsNull() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> algorithm.findIndex(0, null));
        assertEquals("Array must not be null", e.getMessage());
    }

    @Test
    void shouldFindValueIndex() {
        assertEquals(500000, algorithm.findIndex(500451, testData));
    }

    @Test
    void shouldFindFirstValueIndex() {
        assertEquals(2, algorithm.findIndex(0, testData));
    }

    @Test
    void shouldFindLastValueIndex() {
        assertEquals(999999, algorithm.findIndex(999999, testData));
    }

    @Test
    void shouldReturnMinusOneOnIndexFindWhenArrayDoesNotContainValue() {
        assertEquals(-1, algorithm.findIndex(-1, testData));
    }
}