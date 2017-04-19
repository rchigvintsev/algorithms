package org.briarheart.test.util;

import com.google.common.graph.MutableGraph;
import org.briarheart.algorithms.graph.DepthFirstPathsTest;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author Roman Chigvintsev
 */
public class GraphTestUtils {
    private static final Pattern EDGE_PATTERN = Pattern.compile("\\s*(\\d+)\\s*(\\d+)");

    private GraphTestUtils() {
        //no instance
    }

    public static void fillGraph(MutableGraph<Integer> graph, String testDataFileName) throws IOException {
        ClassLoader classLoader = DepthFirstPathsTest.class.getClassLoader();
        URL fileUrl = classLoader.getResource(testDataFileName);
        if (fileUrl == null)
            throw new FileNotFoundException(testDataFileName);

        Scanner scanner = new Scanner(fileUrl.openStream());
        int lineNumber = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (lineNumber < 2) {
                if (lineNumber == 0) { // Number of nodes
                    int nodesNumber = Integer.parseInt(line);
                    for (int i = 0; i < nodesNumber; i++)
                        graph.addNode(i);
                }
                lineNumber++;
                continue;
            }
            Matcher matcher = EDGE_PATTERN.matcher(line);
            if (matcher.find()) {
                int nodeU = Integer.parseInt(matcher.group(1));
                int nodeV = Integer.parseInt(matcher.group(2));
                graph.putEdge(nodeU, nodeV);
            }
            lineNumber++;
        }
    }

    public static <T> void checkCycle(Iterable<T> cycle) {
        assertNotNull(cycle);
        T first = null, last = null;
        for (T node : cycle) {
            if (first == null)
                first = node;
            last = node;
        }
        assertEquals(first, last);
    }
}
