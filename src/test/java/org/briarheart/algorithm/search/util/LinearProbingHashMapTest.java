package org.briarheart.algorithm.search.util;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Roman Chigvintsev
 */
class LinearProbingHashMapTest extends AbstractMapTest {
    @Override
    Map<String, Integer> createMap() {
        return new LinearProbingHashMap<>();
    }

    @Test
    void shouldThrowExceptionOnConstructWhenInitialCapacityIsLessThenZero() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new LinearProbingHashMap<>(-1));
        assertEquals("Initial capacity must not be negative", e.getMessage());
    }
}
