package org.briarheart.algorithms.graph;

import com.google.common.graph.Graph;
import com.google.common.graph.GraphBuilder;
import com.google.common.graph.MutableGraph;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import static org.briarheart.test.util.TestUtils.testIterable;
import static org.junit.Assert.assertEquals;

/**
 * @author Roman Chigvintsev
 */
public class DepthFirstSearchTest {
    private static final String TEST_DATA_FILE_NAME = "tinyG.txt";

    private static Graph<Integer> graph;

    @BeforeClass
    public static void setUp() throws IOException {
        ClassLoader classLoader = DepthFirstSearchTest.class.getClassLoader();
        URL fileUrl = classLoader.getResource(TEST_DATA_FILE_NAME);
        if (fileUrl == null)
            throw new FileNotFoundException(TEST_DATA_FILE_NAME);

        MutableGraph<Integer> mutableGraph = GraphBuilder.undirected().build();
        Scanner scanner = new Scanner(fileUrl.openStream());
        int lineNumber = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (lineNumber < 2) {
                if (lineNumber == 0) { // Number of nodes
                    int nodesNumber = Integer.parseInt(line);
                    for (int i = 0; i < nodesNumber; i++)
                        mutableGraph.addNode(i);
                }
                lineNumber++;
                continue;
            }
            String[] nodes = line.split(" ");
            int nodeU = Integer.parseInt(nodes[0]);
            int nodeV = Integer.parseInt(nodes[1]);
            mutableGraph.putEdge(nodeU, nodeV);
            lineNumber++;
        }
        graph = mutableGraph;
    }

    @Test
    public void testForNode0() {
        testForNodeN(0, false, new Integer[] {0, 1, 2, 3, 4, 5, 6});
    }

    @Test
    public void testForNode9() {
        testForNodeN(9, false, new Integer[] {9, 10, 11, 12});
    }

    private void testForNodeN(Integer node, boolean connected, Integer[] expectedMarkedNodes) {
        DepthFirstSearch<Integer> dfs = new DepthFirstSearch<>(graph, node);
        assertEquals(connected, dfs.getCount() == graph.nodes().size());
        Set<Integer> markedNodes = new HashSet<>();
        for (Integer n : graph.nodes())
            if (dfs.isMarked(n))
                markedNodes.add(n);
        testIterable(markedNodes, expectedMarkedNodes);
    }
}