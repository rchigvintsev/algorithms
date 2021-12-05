package org.briarheart.algorithm.graph;

import com.google.common.collect.Iterables;
import com.google.common.graph.Graph;
import com.google.common.graph.GraphBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.briarheart.test.util.GraphTestUtils.fillGraph;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Roman Chigvintsev
 */
public class BreadthFirstPathsTest extends AbstractGraphAlgorithmTest {
    private static Graph<Integer> graph;

    @BeforeAll
    public static void setUp() throws IOException {
        graph = fillGraph(GraphBuilder.undirected().build(), "tinyCG.txt");
    }

    @Test
    public void doTest() {
        BreadthFirstPaths<Integer> bfs = new BreadthFirstPaths<>(graph, 0);
        assertEquals(0, bfs.distanceTo(0));

        for (Integer node : graph.nodes())
            for (Integer adjacentNode : graph.adjacentNodes(node)) {
                assertTrue(bfs.hasPathTo(node) == bfs.hasPathTo(adjacentNode));
                if (bfs.hasPathTo(node))
                    assertTrue(bfs.distanceTo(adjacentNode) <= bfs.distanceTo(node) + 1);
            }

        Map<Integer, Integer> expectedDistances = new HashMap<>();
        expectedDistances.put(0, 0);
        expectedDistances.put(1, 1);
        expectedDistances.put(2, 1);
        expectedDistances.put(3, 2);
        expectedDistances.put(4, 2);
        expectedDistances.put(5, 1);

        for (Integer v : graph.nodes()) {
            int expectedDistance = expectedDistances.get(v);
            assertEquals(expectedDistance, bfs.distanceTo(v));
            assertNotNull(bfs.pathTo(v));
            assertEquals(expectedDistance + 1, Iterables.size(bfs.pathTo(v)));
        }
    }
}