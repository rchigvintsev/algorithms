package org.briarheart.algorithm.search.util;

import org.junit.jupiter.api.BeforeEach;

/**
 * @author Roman Chigvintsev
 */
public class SeparateChainingHashMapTest extends AbstractHashMapTest {
    @BeforeEach
    public void setUp() {
        map = new SeparateChainingHashMap<>();
    }
}
