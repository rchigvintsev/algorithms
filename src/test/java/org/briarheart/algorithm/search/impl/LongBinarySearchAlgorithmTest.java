package org.briarheart.algorithm.search.impl;

import org.briarheart.algorithm.search.LongSearchAlgorithm;
import org.briarheart.algorithm.search.Searching;
import org.briarheart.algorithm.util.TestFiles;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LongBinarySearchAlgorithmTest {
    private static final String TEST_DATA_FILE_NAME = "tinyW.txt";

    private static LongSearchAlgorithm algorithm;
    private static long[] testData;

    @BeforeAll
    static void beforeAll() throws IOException {
        algorithm = Searching.binaryLong();
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
        assertEquals(7, algorithm.findIndex(33L, testData));
    }

    @Test
    void shouldFindFirstValueIndex() {
        assertEquals(0, algorithm.findIndex(10L, testData));
    }

    @Test
    void shouldFindLastValueIndex() {
        assertEquals(14, algorithm.findIndex(98L, testData));
    }

    @Test
    void shouldReturnMinusOneOnIndexFindWhenArrayDoesNotContainValue() {
        assertEquals(-1, algorithm.findIndex(-1L, testData));
    }
}