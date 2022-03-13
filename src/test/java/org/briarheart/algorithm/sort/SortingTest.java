package org.briarheart.algorithm.sort;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.briarheart.algorithm.sort.SortAlgorithmType.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Roman Chigvintsev
 */
public class SortingTest {
    private static final String[] TEST_DATA_FILE_NAMES = {"tiny.txt", "medTale.txt"};

    private static String[][] unsorted;

    private String[][] unsortedCopy;

    @BeforeAll
    public static void setUpClass() throws IOException {
        ClassLoader classLoader = SortingTest.class.getClassLoader();
        unsorted = new String[TEST_DATA_FILE_NAMES.length][];
        for (int i = 0; i < TEST_DATA_FILE_NAMES.length; i++) {
            URL fileUrl = classLoader.getResource(TEST_DATA_FILE_NAMES[i]);
            if (fileUrl == null)
                throw new FileNotFoundException(TEST_DATA_FILE_NAMES[i]);
            Scanner scanner = new Scanner(fileUrl.openStream());
            List<String> strings = new ArrayList<>();
            while (scanner.hasNext())
                strings.add(scanner.next().trim());
            if (strings.isEmpty())
                throw new IllegalStateException("File " + TEST_DATA_FILE_NAMES[i] + " is empty");
            unsorted[i] = strings.toArray(new String[strings.size()]);
        }
    }

    @BeforeEach
    public void setup() {
        unsortedCopy = new String[unsorted.length][];
        for (int i = 0; i < unsorted.length; i++)
            unsortedCopy[i] = Arrays.copyOf(unsorted[i], unsorted[i].length);
    }

    @Test
    public void testQuickSort() {
        testSortingAlgorithm(QUICK);
    }

    @Test
    public void testQuickSortWithRange() {
        testSortingAlgorithmWithRange(QUICK);
    }

    @Test
    public void testOptimizedQuickSort() {
        testSortingAlgorithm(OPTIMIZED_QUICK);
    }

    @Test
    public void testOptimizedQuickSortWithRange() {
        testSortingAlgorithmWithRange(OPTIMIZED_QUICK);
    }

    @Test
    public void testHeapSort() {
        testSortingAlgorithm(HEAP);
    }

    @Test
    public void testHeapSortWithRange() {
        testSortingAlgorithmWithRange(HEAP);
    }

    private void testSortingAlgorithm(SortAlgorithmType alg) {
        for (String[] a : unsortedCopy)
            assertTrue(isSorted(Sorting.doSort(a, alg)));
    }

    private void testSortingAlgorithmWithRange(SortAlgorithmType alg) {
        for (String[] a : unsortedCopy) {
            int subarrayLength = a.length / 2;
            int lo = (a.length - subarrayLength) / 2;
            int hi = lo + subarrayLength - 1;
            Sorting.doSort(a, lo, hi, alg);
            assertTrue(isSorted(a, lo, hi));
        }

    }

    private <T extends Comparable<? super T>> boolean isSorted(T[] a) {
        return isSorted(a, 0, a.length - 1);
    }

    private <T extends Comparable<? super T>> boolean isSorted(T[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (a[i].compareTo(a[i - 1]) < 0)
                return false;
        return true;
    }
}
