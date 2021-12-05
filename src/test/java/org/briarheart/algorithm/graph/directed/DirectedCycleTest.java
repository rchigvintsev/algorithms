package org.briarheart.algorithm.graph.directed;

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
public class DirectedCycleTest extends AbstractGraphAlgorithmTest {
    private static Graph<Integer> graph;

    @BeforeEach
    public static void setUp() throws IOException {
        graph = fillGraph(GraphBuilder.directed().build(), "tinyDG.txt");
    }

    @Test
    public void shouldFindCycle() {
        DirectedCycle<Integer> cycle = new DirectedCycle<>(graph);
        assertTrue(cycle.hasCycle());
        checkCycle(cycle.getCycle());
    }
}
