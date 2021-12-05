package org.briarheart.algorithm.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TestFiles {
    private static final String[] EMPTY_STRING_ARRAY = {};
    private static final int[] EMPTY_INT_ARRAY = {};
    private static final long[] EMPTY_LONG_ARRAY = {};
    private static final double[] EMPTY_DOUBLE_ARRAY = {};

    private TestFiles() {
        //no instance
    }

    public static String[] readAllWords(String fileName) throws IOException {
        ClassLoader classLoader = TestFiles.class.getClassLoader();
        URL fileUrl = classLoader.getResource(fileName);
        if (fileUrl == null) {
            throw new FileNotFoundException(fileName);
        }

        Scanner scanner = new Scanner(fileUrl.openStream());
        List<String> words = new ArrayList<>();
        while (scanner.hasNext()) {
            words.add(scanner.next().trim());
        }
        return !words.isEmpty() ? words.toArray(new String[0]) : EMPTY_STRING_ARRAY;
    }

    public static int[] readAllInts(String fileName) throws IOException {
        ClassLoader classLoader = TestFiles.class.getClassLoader();
        URL fileUrl = classLoader.getResource(fileName);
        if (fileUrl == null) {
            throw new FileNotFoundException(fileName);
        }

        Scanner scanner = new Scanner(fileUrl.openStream());
        List<Integer> integers = new ArrayList<>();
        while (scanner.hasNext()) {
            integers.add(Integer.parseInt(scanner.next().trim()));
        }
        if (integers.isEmpty()) {
            return EMPTY_INT_ARRAY;
        }
        return integers.stream().mapToInt(Integer::valueOf).toArray();
    }

    public static long[] readAllLongs(String fileName) throws IOException {
        ClassLoader classLoader = TestFiles.class.getClassLoader();
        URL fileUrl = classLoader.getResource(fileName);
        if (fileUrl == null) {
            throw new FileNotFoundException(fileName);
        }

        Scanner scanner = new Scanner(fileUrl.openStream());
        List<Long> longs = new ArrayList<>();
        while (scanner.hasNext()) {
            longs.add(Long.parseLong(scanner.next().trim()));
        }
        if (longs.isEmpty()) {
            return EMPTY_LONG_ARRAY;
        }
        return longs.stream().mapToLong(Long::valueOf).toArray();
    }

    public static double[] readAllDoubles(String fileName) throws IOException {
        ClassLoader classLoader = TestFiles.class.getClassLoader();
        URL fileUrl = classLoader.getResource(fileName);
        if (fileUrl == null) {
            throw new FileNotFoundException(fileName);
        }

        Scanner scanner = new Scanner(fileUrl.openStream());
        List<Double> doubles = new ArrayList<>();
        while (scanner.hasNext()) {
            doubles.add(Double.parseDouble(scanner.next().trim()));
        }
        if (doubles.isEmpty()) {
            return EMPTY_DOUBLE_ARRAY;
        }
        return doubles.stream().mapToDouble(Double::valueOf).toArray();
    }
}
