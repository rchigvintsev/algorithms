package org.briarheart.algorithms.search.util;

import org.junit.Before;

/**
 * @author Roman Chigvintsev
 */
public class LinearProbingHashMapTest extends AbstractHashMapTest {
    @Before
    public void setUp() throws Exception {
        map = new LinearProbingHashMap<>();
    }
}
