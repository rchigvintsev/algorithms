package org.briarheart.algorithms.search.util;

import org.junit.Before;

/**
 * @author Roman Chigvintsev
 */
public class SeparateChainingHashMapTest extends AbstractHashMapTest {
    @Before
    public void setUp() {
        map = new SeparateChainingHashMap<>();
    }
}
