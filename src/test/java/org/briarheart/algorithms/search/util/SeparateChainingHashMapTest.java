package org.briarheart.algorithms.search.util;

import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * @author Roman Chigvintsev
 */
public class SeparateChainingHashMapTest {
    private SeparateChainingHashMap<String, Integer> map;

    @Before
    public void setUp() {
        map = new SeparateChainingHashMap<>();
    }

    @Test
    public void testSize() {
        assertEquals(0, map.size());
        map.put("null", null);
        assertEquals(0, map.size());
        map.put("one", 1);
        assertEquals(1, map.size());
        map.put("two", 2);
        assertEquals(2, map.size());
        map.remove("one");
        assertEquals(1, map.size());
        map.remove("two");
        assertEquals(0, map.size());
    }

    @Test
    public void testIsEmpty() {
        assertTrue(map.isEmpty());
        map.put("one", 1);
        assertFalse(map.isEmpty());
        map.remove("one");
        assertTrue(map.isEmpty());
    }

    @Test
    public void testContainsKey() {
        assertFalse(map.containsKey("one"));

        map.put("one", 1);
        assertTrue(map.containsKey("one"));

        map.put("two", 2);
        assertTrue(map.containsKey("one"));
        assertTrue(map.containsKey("two"));

        map.remove("one");
        assertFalse(map.containsKey("one"));
        assertTrue(map.containsKey("two"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testContainsKeyWithNullKey() {
        map.containsKey(null);
    }

    @Test
    public void testContainsValue() {
        assertFalse(map.containsValue(null));
        assertFalse(map.containsValue(1));

        map.put("one", 1);
        assertTrue(map.containsValue(1));

        map.put("two", 2);
        assertTrue(map.containsValue(1));
        assertTrue(map.containsValue(2));

        map.remove("one");
        assertFalse(map.containsValue(1));
        assertTrue(map.containsValue(2));
    }

    @Test
    public void testPut() throws Exception {
        map.put("one", 1);
        assertEquals((Object) 1, map.get("one"));
        assertEquals(1, map.size());

        map.put("two", 2);
        assertEquals((Object) 1, map.get("one"));
        assertEquals((Object) 2, map.get("two"));
        assertEquals(2, map.size());

        map.put("three", 3);
        assertEquals((Object) 1, map.get("one"));
        assertEquals((Object) 2, map.get("two"));
        assertEquals((Object) 3, map.get("three"));
        assertEquals(3, map.size());

        assertEquals((Object) 1, map.put("one", -1));
        assertEquals((Object) (-1), map.get("one"));
        assertEquals(3, map.size());

        map.put("two", null);
        assertEquals(2, map.size());
        assertNull(map.get("two"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPutWithNullKey() {
        map.put(null, 0);
    }

    @Test
    public void testGet() {
        assertNull(map.get("null"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetWithNullKey() {
        map.get(null);
    }

    @Test
    public void testRemove() {
        assertEquals(0, map.size());

        assertNull(map.remove("null"));
        assertEquals(0, map.size());

        map.put("one", 1);
        assertEquals(1, map.size());

        assertEquals((Object) 1, map.remove("one"));
        assertEquals(0, map.size());
        assertNull(map.remove("one"));

        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        assertEquals(3, map.size());

        assertEquals((Object) 2, map.remove("two"));
        assertEquals(2, map.size());
        assertNull(map.remove("two"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveWithNullKey() {
        map.remove(null);
    }

    @Test
    public void testPutAll() {
        assertEquals(0, map.size());

        map.putAll(Collections.emptyMap());
        assertEquals(0, map.size());

        Map<String, Integer> anotherMap = new HashMap<>();
        anotherMap.put("one", 1);
        anotherMap.put("two", 2);
        anotherMap.put("three", 3);

        map.putAll(anotherMap);
        assertEquals(3, map.size());
        assertEquals((Object) 1, map.get("one"));
        assertEquals((Object) 2, map.get("two"));
        assertEquals((Object) 3, map.get("three"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPutAllWithNullMap() {
        map.putAll(null);
    }

    @Test
    public void testClear() {
        map.clear();

        map.put("one", 1);
        assertFalse(map.isEmpty());
        assertEquals((Object) 1, map.get("one"));

        map.clear();
        assertTrue(map.isEmpty());
        assertNull(map.get("one"));

        map.put("one", 1);
        map.put("two", 2);
        assertFalse(map.isEmpty());
        assertEquals((Object) 1, map.get("one"));
        assertEquals((Object) 2, map.get("two"));

        map.clear();
        assertTrue(map.isEmpty());
        assertNull(map.get("one"));
        assertNull(map.get("two"));
    }

    @Test
    public void testKeySet() {
        assertNotNull(map.keySet());
        assertTrue(map.keySet().isEmpty());

        map.put("one", 1);
        assertEquals(1, map.keySet().size());
        assertTrue(map.keySet().contains("one"));

        map.remove("one");
        assertNotNull(map.keySet());
        assertTrue(map.keySet().isEmpty());

        map.put("one", 1);
        map.put("two", 2);
        assertEquals(2, map.keySet().size());
        assertTrue(map.keySet().contains("one"));
        assertTrue(map.keySet().contains("two"));
    }

    @Test
    public void testValues() {
        assertNotNull(map.values());
        assertTrue(map.values().isEmpty());

        map.put("one", 1);
        assertEquals(1, map.values().size());
        assertTrue(map.values().contains(1));

        map.remove("one");
        assertNotNull(map.values());
        assertTrue(map.values().isEmpty());

        map.put("one", 1);
        map.put("two", 2);
        assertEquals(2, map.values().size());
        assertTrue(map.values().contains(1));
        assertTrue(map.values().contains(2));
    }

    @Test
    public void testEntrySet() {
        assertNotNull(map.entrySet());
        assertTrue(map.entrySet().isEmpty());

        map.put("one", 1);
        testEntrySet(map.entrySet(), "one", 1);

        map.remove("one");
        assertNotNull(map.entrySet());
        assertTrue(map.entrySet().isEmpty());

        map.put("one", 1);
        map.put("two", 2);
        testEntrySet(map.entrySet(), "one", 1, "two", 2);
    }

    private void testEntrySet(Set<Map.Entry<String, Integer>> entries, Object... expectedKeyValuePairs) {
        assertNotNull(entries);
        assertEquals(expectedKeyValuePairs.length / 2, entries.size());

        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < expectedKeyValuePairs.length; i += 2) {
            String key = (String) expectedKeyValuePairs[i];
            Integer value = (Integer) expectedKeyValuePairs[i + 1];
            map.put(key, value);
        }

        for (Map.Entry<String, Integer> entry : entries)
            assertEquals(map.get(entry.getKey()), entry.getValue());
    }
}
