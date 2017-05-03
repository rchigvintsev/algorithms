package org.briarheart.algorithms.graph.directed;

import com.google.common.graph.Graph;
import com.google.common.graph.GraphBuilder;
import org.briarheart.algorithms.graph.AbstractGraphAlgorithmTest;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

import static org.briarheart.test.util.GraphTestUtils.checkCycle;
import static org.briarheart.test.util.GraphTestUtils.fillGraph;
import static org.junit.Assert.assertTrue;

/**
 * @author Roman Chigvintsev
 */
public class DirectedCycleTest extends AbstractGraphAlgorithmTest {
    private static Graph<Integer> graph;

    @BeforeClass
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
