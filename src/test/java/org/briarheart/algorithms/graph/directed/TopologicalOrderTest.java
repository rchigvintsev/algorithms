package org.briarheart.algorithms.graph.directed;

import com.google.common.collect.Iterables;
import com.google.common.graph.Graph;
import com.google.common.graph.GraphBuilder;
import com.google.common.graph.MutableGraph;
import org.briarheart.algorithms.graph.AbstractGraphAlgorithmTest;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.Iterator;

import static org.briarheart.test.util.GraphTestUtils.fillGraph;
import static org.junit.Assert.*;

/**
 * @author Roman Chigvintsev
 */
public class TopologicalOrderTest extends AbstractGraphAlgorithmTest {
    private static Graph<String> graph;

    @BeforeClass
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

    @Test(expected = IllegalArgumentException.class)
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