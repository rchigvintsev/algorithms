package org.briarheart.algorithm.graph.directed;

import com.google.common.graph.Graph;
import com.google.common.graph.GraphBuilder;
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
public class KosarajuStronglyConnectedComponentsTest {
    private static Graph<Integer> graph;

    @BeforeEach
    public static void setUp() throws IOException {
        graph = fillGraph(GraphBuilder.directed().build(), "tinyDG.txt");
    }

    @Test
    public void shouldFindStronglyConnectedComponents() {
        KosarajuStronglyConnectedComponents<Integer> cc = new KosarajuStronglyConnectedComponents<>(graph);
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
                for (Integer anotherNode : graph.nodes())
                    assertEquals(cc.componentId(anotherNode) == componentId, cc.connected(node, anotherNode));
            }
        }
    }
}