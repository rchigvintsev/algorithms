package org.briarheart.algorithms.search;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

/**
 * @author Roman Chigvintsev
 */
public class SearchTest {
    private static final String TEST_DATA_FILE_NAME = "tinyST.txt";

    private static String[] searchExample;

    @BeforeClass
    public static void setupClass() throws IOException {
        ClassLoader classLoader = SearchTest.class.getClassLoader();
        URL fileUrl = classLoader.getResource(TEST_DATA_FILE_NAME);
        if (fileUrl == null)
            throw new FileNotFoundException(TEST_DATA_FILE_NAME);
        Scanner scanner = new Scanner(fileUrl.openStream());
        List<String> strings = new ArrayList<>();
        while (scanner.hasNext())
            strings.add(scanner.next().trim());
        if (strings.isEmpty())
            throw new IllegalStateException("File " + TEST_DATA_FILE_NAME + " is empty");
        searchExample = strings.toArray(new String[strings.size()]);
        Arrays.sort(searchExample);
    }

    @Test
    public void testBinarySearch() {
        SearchingAlgorithm alg = SearchingAlgorithm.BINARY;

        assertEquals(0, Search.doSearch("A", searchExample, alg));
        assertEquals(12, Search.doSearch("X", searchExample, alg));
        assertEquals(6, Search.doSearch("H", searchExample, alg));
        assertEquals(-1, Search.doSearch("B", searchExample, alg));
    }
}
