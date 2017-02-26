package org.briarheart.algorithms.sort;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.briarheart.algorithms.sort.SortingAlgorithm.*;

/**
 * @author Roman Chigvintsev
 */
public class SortTest {
    private static final String[] TEST_DATA_FILE_NAMES = {"tiny.txt", "medTale.txt"};

    private static String[][] unsorted;

    private String[][] unsortedCopy;

    @BeforeClass
    public static void setUpClass() throws IOException {
        ClassLoader classLoader = SortTest.class.getClassLoader();
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

    @Before
    public void setup() {
        unsortedCopy = new String[unsorted.length][];
        for (int i = 0; i < unsorted.length; i++)
            unsortedCopy[i] = Arrays.copyOf(unsorted[i], unsorted[i].length);
    }

    @Test
    public void testSelectionSort() {
        testSortingAlgorithm(SELECTION);
    }

    @Test
    public void testSelectionSortWithRange() {
        testSortingAlgorithmWithRange(SELECTION);
    }

    @Test
    public void testInsertionSort() {
        testSortingAlgorithm(INSERTION);
    }

    @Test
    public void testInsertionSortWithRange() {
        testSortingAlgorithmWithRange(INSERTION);
    }

    @Test
    public void testOptimizedInsertionSort() {
        testSortingAlgorithm(OPTIMIZED_INSERTION);
    }

    @Test
    public void testOptimizedInsertionSortWithRange() {
        testSortingAlgorithmWithRange(OPTIMIZED_INSERTION);
    }

    @Test
    public void testShellSort() {
        testSortingAlgorithm(SHELL);
    }

    @Test
    public void testShellSortWithRange() {
        testSortingAlgorithmWithRange(SHELL);
    }

    @Test
    public void testTopDownMergeSort() {
        testSortingAlgorithm(TOP_DOWN_MERGE);
    }

    @Test
    public void testTopDownMergeSortWithRange() {
        testSortingAlgorithmWithRange(TOP_DOWN_MERGE);
    }

    @Test
    public void testOptimizedTopDownMergeSort() {
        testSortingAlgorithm(OPTIMIZED_TOP_DOWN_MERGE);
    }

    @Test
    public void testOptimizedTopDownMergeSortWithRange() {
        testSortingAlgorithmWithRange(OPTIMIZED_TOP_DOWN_MERGE);
    }

    @Test
    public void testBottomUpMergeSort() {
        testSortingAlgorithm(BOTTOM_UP_MERGE);
    }

    @Test
    public void testBottomUpMergeSortWithRange() {
        testSortingAlgorithmWithRange(BOTTOM_UP_MERGE);
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

    private void testSortingAlgorithm(SortingAlgorithm alg) {
        for (String[] a : unsortedCopy)
            Assert.assertTrue(isSorted(Sort.doSort(a, alg)));
    }

    private void testSortingAlgorithmWithRange(SortingAlgorithm alg) {
        for (String[] a : unsortedCopy) {
            int subarrayLength = a.length / 2;
            int lo = (a.length - subarrayLength) / 2;
            int hi = lo + subarrayLength - 1;
            Sort.doSort(a, lo, hi, alg);
            Assert.assertTrue(isSorted(a, lo, hi));
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
