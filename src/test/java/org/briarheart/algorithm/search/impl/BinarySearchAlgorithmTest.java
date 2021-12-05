package org.briarheart.algorithm.search.impl;

import org.briarheart.algorithm.search.Searching;
import org.briarheart.algorithm.search.SearchingAlgorithm;
import org.briarheart.algorithm.util.TestFiles;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BinarySearchAlgorithmTest {
    private static final String TEST_DATA_FILE_NAME = "tinyST.txt";

    private static SearchingAlgorithm<String> algorithm;
    private static String[] testData;

    @BeforeAll
    static void beforeAll() throws IOException {
        algorithm = Searching.binarySearch();
        testData = TestFiles.readAllWords(TEST_DATA_FILE_NAME);
        Arrays.sort(testData);
    }

    @Test
    void shouldThrowExceptionOnFindIndexWhenKeyIsNull() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> algorithm.findIndex(null, testData));
        assertEquals("Key must not be null", e.getMessage());
    }

    @Test
    void shouldThrowExceptionOnIndexFindWhenArrayIsNull() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> algorithm.findIndex("A", null));
        assertEquals("Array must not be null", e.getMessage());
    }

    @Test
    void shouldFindValueIndex() {
        assertEquals(6, algorithm.findIndex("H", testData));
    }

    @Test
    void shouldFindFirstValueIndex() {
        assertEquals(0, algorithm.findIndex("A", testData));
    }

    @Test
    void shouldFindLastValueIndex() {
        assertEquals(12, algorithm.findIndex("X", testData));
    }

    @Test
    void shouldReturnMinusOneOnIndexFindWhenArrayDoesNotContainValue() {
        assertEquals(-1, algorithm.findIndex("B", testData));
    }
}