package org.briarheart.algorithms.graph;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author Roman Chigvintsev
 */
public class CycleTest extends AbstractGraphAlgorithmTest {
    @BeforeClass
    public static void setUp() throws IOException {
        setUp("tinyG.txt");
    }

    @Test
    public void shouldFindCycle() {
        Cycle<Integer> cycle = new Cycle<>(graph);
        assertTrue(cycle.hasCycle());
        assertNotNull(cycle.getCycle());
    }
}