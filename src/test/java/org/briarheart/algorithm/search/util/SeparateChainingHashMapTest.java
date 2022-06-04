package org.briarheart.algorithm.search.util;

import java.util.Map;

/**
 * @author Roman Chigvintsev
 */
class SeparateChainingHashMapTest extends AbstractMapTest {
    Map<String, Integer> createMap() {
        return new SeparateChainingHashMap<>();
    }
}
