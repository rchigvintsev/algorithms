package org.briarheart.algorithms.graph;

import com.google.common.collect.Iterables;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * @author Roman Chigvintsev
 */
public class DepthFirstPathsTest extends AbstractGraphAlgorithmTest {
    @BeforeClass
    public static void setUp() throws IOException {
        setUp("tinyCG.txt");
    }

    @Test
    public void doTest() {
        DepthFirstPaths<Integer> dfs = new DepthFirstPaths<>(graph, 0);
        for (Integer n : graph.nodes()) {
            assertTrue(dfs.hasPathTo(n));
            assertNotNull(dfs.pathTo(n));
            assertFalse(Iterables.isEmpty(dfs.pathTo(n)));
        }
    }
}
