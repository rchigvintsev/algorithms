package org.briarheart.algorithm.graph;

import com.google.common.collect.Iterables;
import com.google.common.graph.Graph;
import com.google.common.graph.GraphBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.briarheart.test.util.GraphTestUtils.fillGraph;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Roman Chigvintsev
 */
public class DepthFirstPathsTest extends AbstractGraphAlgorithmTest {
    private static Graph<Integer> graph;

    @BeforeAll
    public static void setUp() throws IOException {
        graph = fillGraph(GraphBuilder.undirected().build(), "tinyCG.txt");
    }

    @Test
    public void doTest() {
        DepthFirstPaths<Integer> dfs = new DepthFirstPaths<>(graph, 0);
        for (Integer n : graph.nodes()) {
            assertTrue(dfs.hasPathTo(n));
            assertNotNull(dfs.pathTo(n));
            assertFalse(Iterables.isEmpty(dfs.pathTo(n)));
        }
    }
}
