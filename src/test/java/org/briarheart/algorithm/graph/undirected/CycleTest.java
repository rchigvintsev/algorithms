package org.briarheart.algorithm.graph.undirected;

import com.google.common.graph.Graph;
import com.google.common.graph.GraphBuilder;
import org.briarheart.algorithm.graph.AbstractGraphAlgorithmTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.briarheart.test.util.GraphTestUtils.checkCycle;
import static org.briarheart.test.util.GraphTestUtils.fillGraph;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Roman Chigvintsev
 */
public class CycleTest extends AbstractGraphAlgorithmTest {
    private static Graph<Integer> graph;

    @BeforeEach
    public static void setUp() throws IOException {
        graph = fillGraph(GraphBuilder.undirected().build(), "tinyG.txt");
    }

    @Test
    public void shouldFindCycle() {
        Cycle<Integer> cycle = new Cycle<>(graph);
        assertTrue(cycle.hasCycle());
        checkCycle(cycle.getCycle());
    }
}