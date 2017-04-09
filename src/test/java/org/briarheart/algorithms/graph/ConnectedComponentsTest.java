package org.briarheart.algorithms.graph;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Roman Chigvintsev
 */
public class ConnectedComponentsTest extends AbstractGraphAlgorithmTest {
    @BeforeClass
    public static void setUp() throws IOException {
        setUp("tinyG.txt");
    }

    @Test
    public void doTest() {
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
