package org.briarheart.algorithm.graph.undirected;

import com.google.common.graph.Graph;
import com.google.common.graph.GraphBuilder;
import org.briarheart.algorithm.graph.AbstractGraphAlgorithmTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.briarheart.test.util.GraphTestUtils.fillGraph;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Roman Chigvintsev
 */
public class ConnectedComponentsTest extends AbstractGraphAlgorithmTest {
    private static Graph<Integer> graph;

    @BeforeEach
    public static void setUp() throws IOException {
        graph = fillGraph(GraphBuilder.undirected().build(), "tinyG.txt");
    }

    @Test
    public void shouldFindConnectedComponents() {
        ConnectedComponents<Integer> cc = new ConnectedComponents<>(graph);
        assertTrue(cc.count() > 0);
        Map<Integer, Set<Integer>> components = new HashMap<>();
        for (Integer node : graph.nodes()) {
            Set<Integer> component = components.computeIfAbsent(cc.componentId(node), (id) -> new HashSet<>());
            component.add(node);
        }
        assertEquals(cc.count(), components.size());

        for (Integer componentId : components.keySet()) {
            Set<Integer> component = components.get(componentId);
            for (Integer node : component) {
                assertEquals(component.size(), cc.componentSize(node));
                for (Integer anotherNode : graph.nodes())
                    assertEquals(cc.componentId(anotherNode) == componentId, cc.connected(node, anotherNode));
            }
        }
    }
}
