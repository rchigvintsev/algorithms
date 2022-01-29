package org.briarheart.algorithm.search.impl;

import org.briarheart.algorithm.search.IntSearchAlgorithm;
import org.briarheart.algorithm.search.Searching;
import org.briarheart.algorithm.util.TestFiles;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class IntBinarySearchAlgorithmTest {
    private static final String TEST_DATA_FILE_NAME = "tinyW.txt";

    private static IntSearchAlgorithm algorithm;
    private static int[] testData;

    @BeforeAll
    static void beforeAll() throws IOException {
        algorithm = Searching.binaryInt();
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
        assertEquals(7, algorithm.findIndex(33, testData));
    }

    @Test
    void shouldFindFirstValueIndex() {
        assertEquals(0, algorithm.findIndex(10, testData));
    }

    @Test
    void shouldFindLastValueIndex() {
        assertEquals(14, algorithm.findIndex(98, testData));
    }

    @Test
    void shouldReturnMinusOneOnIndexFindWhenArrayDoesNotContainValue() {
        assertEquals(-1, algorithm.findIndex(-1, testData));
    }
}