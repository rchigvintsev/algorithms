package org.briarheart.algorithms.graph.directed;

import com.google.common.collect.Iterables;
import com.google.common.graph.Graph;
import com.google.common.graph.GraphBuilder;
import org.briarheart.algorithms.graph.AbstractGraphAlgorithmTest;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

import static org.briarheart.test.util.GraphTestUtils.fillGraph;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

/**
 * @author Roman Chigvintsev
 */
public class DepthFirstOrderTest extends AbstractGraphAlgorithmTest {
    private static Graph<Integer> graph;

    @BeforeClass
    public static void setUp() throws IOException {
        graph = fillGraph(GraphBuilder.directed().build(), "tinyDAG.txt");
    }

    @Test
    public void shouldComputeOrder() {
        DepthFirstOrder<Integer> dfo = new DepthFirstOrder<>(graph);
        assertNotNull(dfo.getOrder());
        assertFalse(Iterables.isEmpty(dfo.getOrder()));
    }
}