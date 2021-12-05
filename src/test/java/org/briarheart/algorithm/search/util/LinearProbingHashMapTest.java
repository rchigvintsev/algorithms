package org.briarheart.algorithm.search.util;

import org.junit.jupiter.api.BeforeEach;

/**
 * @author Roman Chigvintsev
 */
public class LinearProbingHashMapTest extends AbstractHashMapTest {
    @BeforeEach
    public void setUp() throws Exception {
        map = new LinearProbingHashMap<>();
    }
}
