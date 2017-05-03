package org.briarheart.test.util;

import com.google.common.graph.Graph;
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
    private static final Pattern EDGE_PATTERN_1 = Pattern.compile("^(?: )*(\\d+)(?: )+(\\d+)");
    private static final Pattern EDGE_PATTERN_2 = Pattern.compile("([\\w ]+)/?");

    private GraphTestUtils() {
        //no instance
    }

    @SuppressWarnings("unchecked")
    public static <T> Graph<T> fillGraph(MutableGraph<T> graph, String testDataFileName) throws IOException {
        ClassLoader classLoader = DepthFirstPathsTest.class.getClassLoader();
        URL fileUrl = classLoader.getResource(testDataFileName);
        if (fileUrl == null)
            throw new FileNotFoundException(testDataFileName);

        Scanner scanner = new Scanner(fileUrl.openStream());
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            Matcher matcher = EDGE_PATTERN_1.matcher(line);
            if (matcher.find()) {
                Integer nodeU = Integer.parseInt(matcher.group(1));
                Integer nodeV = Integer.parseInt(matcher.group(2));
                graph.putEdge((T) nodeU, (T) nodeV);
            } else {
                matcher = EDGE_PATTERN_2.matcher(line);
                if (matcher.find()) {
                    String node = matcher.group(1);
                    while (matcher.find())
                        graph.putEdge((T) node, (T) matcher.group(1));
                }
            }
        }

        return graph;
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
