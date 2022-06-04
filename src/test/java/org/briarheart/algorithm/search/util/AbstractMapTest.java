package org.briarheart.algorithm.search.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Roman Chigvintsev
 */
abstract class AbstractMapTest {
    private Map<String, Integer> map;

    abstract Map<String, Integer> createMap();

    @BeforeEach
    void setUp() {
        map = createMap();
    }

    @Test
    void shouldReturnZeroSizeWhenMapIsEmpty() {
        assertEquals(0, map.size());
    }

    @Test
    void shouldNotCreateMapEntryWithNullValue() {
        map.put("null", null);
        assertEquals(0, map.size());
    }

    @Test
    void shouldIncrementMapSizeOnPut() {
        map.put("one", 1);
        assertEquals(1, map.size());
        map.put("two", 2);
        assertEquals(2, map.size());
    }

    @Test
    void shouldDecrementMapSizeOnRemove() {
        map.put("one", 1);
        map.put("two", 2);
        assertEquals(2, map.size());

        map.remove("one");
        assertEquals(1, map.size());
        map.remove("two");
        assertEquals(0, map.size());
    }

    @Test
    void shouldReturnTrueOnEmptyCheckWhenMapIsEmpty() {
        assertTrue(map.isEmpty());
    }

    @Test
    void shouldReturnFalseOnEmptyCheckWhenMapIsNotEmpty() {
        map.put("one", 1);
        assertFalse(map.isEmpty());
    }

    @Test
    void shouldReturnTrueOnEmptyCheckWhenLastMapEntryIsRemoved() {
        map.put("one", 1);
        assertFalse(map.isEmpty());

        map.remove("one");
        assertTrue(map.isEmpty());
    }

    @Test
    void shouldReturnFalseOnContainsKeyCheckWhenKeyIsNotFound() {
        assertFalse(map.containsKey("one"));
    }

    @Test
    void shouldReturnTrueOnContainsKeyCheckWhenKeyIsFound() {
        map.put("one", 1);
        assertTrue(map.containsKey("one"));
    }

    @Test
    void shouldReturnFalseOnContainsKeyCheckWhenMapEntryIsRemoved() {
        map.put("one", 1);
        assertTrue(map.containsKey("one"));

        map.remove("one");
        assertFalse(map.containsKey("one"));
    }

    @Test
    void shouldThrowExceptionOnContainsKeyCheckWhenKeyIsNull() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> map.containsKey(null));
        assertEquals("Key must not be null", e.getMessage());
    }

    @Test
    void shouldReturnFalseOnContainsValueCheckWhenValueIsNotFound() {
        assertFalse(map.containsValue(1));
    }

    @Test
    void shouldReturnTrueOnContainsValueCheckWhenValueIsFound() {
        map.put("one", 1);
        assertTrue(map.containsValue(1));
    }

    @Test
    void shouldReturnFalseOnContainsValueCheckWhenMapEntryIsRemoved() {
        map.put("one", 1);
        assertTrue(map.containsValue(1));

        map.remove("one");
        assertFalse(map.containsValue(1));
    }

    @Test
    void shouldReturnPreviousValueOnPut() {
        map.put("one", 1);
        assertEquals((Object) 1, map.put("one", -1));
    }

    @Test
    void shouldRemoveMapEntryOnPutWhenValueIsNull() {
        map.put("one", 1);
        map.put("one", null);
        assertNull(map.get("one"));
    }

    @Test
    void shouldThrowExceptionOnPutWhenKeyIsNull() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> map.put(null, 0));
        assertEquals("Key must not be null", e.getMessage());
    }

    @Test
    void shouldReturnNullOnGetWhenMapEntryIsNotFound() {
        assertNull(map.get("null"));
    }

    @Test
    void shouldReturnStoredValueOnGet() {
        map.put("one", 1);
        assertEquals((Object) 1, map.get("one"));
    }

    @Test
    void shouldThrowExceptionOnGetWhenKeyIsNull() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> map.get(null));
        assertEquals("Key must not be null", e.getMessage());
    }

    @Test
    void shouldReturnNullOnRemoveWhenMapEntryIsNotFound() {
        assertNull(map.remove("null"));
    }

    @Test
    void shouldRemoveMapEntry() {
        map.put("one", 1);
        assertEquals((Object) 1, map.remove("one"));
    }

    @Test
    void shouldThrowExceptionOnRemoveWhenKeyIsNull() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> map.remove(null));
        assertEquals("Key must not be null", e.getMessage());
    }

    @Test
    void shouldStoreAllMapEntries() {
        Map<String, Integer> anotherMap = new HashMap<>();
        anotherMap.put("one", 1);
        anotherMap.put("two", 2);
        anotherMap.put("three", 3);
        map.putAll(anotherMap);
        assertEquals(3, map.size());
    }

    @Test
    void shouldThrowExceptionOnPutAllWhenMapIsNull() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> map.putAll(null));
        assertEquals("Map must not be null", e.getMessage());
    }

    @Test
    void shouldClearMap() {
        map.put("one", 1);
        assertFalse(map.isEmpty());

        map.clear();
        assertTrue(map.isEmpty());
    }

    @Test
    void shouldReturnEmptyKeySetWhenMapIsEmpty() {
        Set<String> keys = map.keySet();
        assertNotNull(keys);
        assertTrue(keys.isEmpty());
    }

    @Test
    void shouldReturnMapKeys() {
        map.put("one", 1);
        Set<String> keys = map.keySet();
        assertNotNull(keys);
        assertEquals(1, keys.size());
        assertTrue(keys.contains("one"));
    }

    @Test
    void shouldReturnEmptyValueCollectionWhenMapIsEmpty() {
        Collection<Integer> values = map.values();
        assertNotNull(values);
        assertTrue(values.isEmpty());
    }

    @Test
    void shouldReturnMapValues() {
        map.put("one", 1);

        Collection<Integer> values = map.values();
        assertEquals(1, values.size());
        assertTrue(values.contains(1));
    }

    @Test
    void shouldReturnEmptyEntrySetWhenMapIsEmpty() {
        Set<Map.Entry<String, Integer>> entries = map.entrySet();
        assertNotNull(entries);
        assertTrue(entries.isEmpty());
    }

    @Test
    void shouldReturnMapEntries() {
        map.put("one", 1);

        Set<Map.Entry<String, Integer>> entries = map.entrySet();
        assertEquals(1, entries.size());
        Map.Entry<String, Integer> entry = entries.iterator().next();
        assertEquals("one", entry.getKey());
        assertEquals(1, entry.getValue());
    }
}
