package org.briarheart.algorithms.graph.directed;

import com.google.common.graph.Graph;
import com.google.common.graph.GraphBuilder;
import com.google.common.graph.MutableGraph;
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
public class CycleTest extends AbstractGraphAlgorithmTest {
    private static Graph<Integer> graph;

    @BeforeClass
    public static void setUp() throws IOException {
        MutableGraph<Integer> mutableGraph = GraphBuilder.directed().build();
        fillGraph(mutableGraph, "tinyDG.txt");
        graph = mutableGraph;
    }

    @Test
    public void shouldFindCycle() {
        Cycle<Integer> cycle = new Cycle<>(graph);
        assertTrue(cycle.hasCycle());
        checkCycle(cycle.getCycle());
    }
}