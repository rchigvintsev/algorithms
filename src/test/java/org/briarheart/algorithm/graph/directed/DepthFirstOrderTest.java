package org.briarheart.algorithm.graph.directed;

import com.google.common.collect.Iterables;
import com.google.common.graph.Graph;
import com.google.common.graph.GraphBuilder;
import org.briarheart.algorithm.graph.AbstractGraphAlgorithmTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.briarheart.test.util.GraphTestUtils.fillGraph;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author Roman Chigvintsev
 */
public class DepthFirstOrderTest extends AbstractGraphAlgorithmTest {
    private static Graph<Integer> graph;

    @BeforeAll
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