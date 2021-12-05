package org.briarheart.algorithm.graph.directed;

import com.google.common.collect.Iterables;
import com.google.common.graph.Graph;
import com.google.common.graph.GraphBuilder;
import com.google.common.graph.MutableGraph;
import org.briarheart.algorithm.graph.AbstractGraphAlgorithmTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Iterator;

import static org.briarheart.test.util.GraphTestUtils.fillGraph;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Roman Chigvintsev
 */
public class TopologicalOrderTest extends AbstractGraphAlgorithmTest {
    private static Graph<String> graph;

    @BeforeEach
    public static void setUp() throws IOException {
        graph = fillGraph(GraphBuilder.directed().build(), "jobs.txt");
    }

    @Test
    public void shouldFindTopologicalOrder() {
        TopologicalOrder<String> topological = new TopologicalOrder<>(graph);
        assertTrue(topological.hasOrder());
        assertNotNull(topological.getOrder());
        assertFalse(Iterables.isEmpty(topological.getOrder()));
    }

    @Test
    public void shouldThrowExceptionWhenGraphHasCycle() {
        MutableGraph<String> graphWithCycle = GraphBuilder.directed().build();
        graphWithCycle.putEdge("A", "B");
        graphWithCycle.putEdge("B", "A");
        new TopologicalOrder<>(graphWithCycle);
    }

    @Test
    public void shouldIncreaseRankOfNodeInTopologicalOrder() {
        TopologicalOrder<String> topological = new TopologicalOrder<>(graph);
        Iterator<String> orderIterator = topological.getOrder().iterator();
        int i = 0;
        while (orderIterator.hasNext())
            assertEquals(i++, topological.rankOf(orderIterator.next()));
    }
}