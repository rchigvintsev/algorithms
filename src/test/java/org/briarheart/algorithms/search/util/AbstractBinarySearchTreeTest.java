package org.briarheart.algorithms.search.util;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

/**
 * @author Roman Chigvintsev
 */
public abstract class AbstractBinarySearchTreeTest {
    protected BinarySearchTree<String, Integer> bst;

    @Test
    public void testPut() {
        bst.put("0", 0);
        assertEquals(1, bst.size());
        assertEquals((Object) 0, bst.get("0"));

        bst.put("1", 1);
        assertEquals(2, bst.size());
        assertEquals((Object) 1, bst.get("1"));

        bst.put("1", null);
        assertEquals(1, bst.size());
        assertNull(bst.get("1"));

        bst.put("0", null);
        assertEquals(0, bst.size());
        assertNull(bst.get("0"));
    }

    @Test
    public void testRemove() {
        bst.put("0", 0);
        assertEquals(1, bst.size());
        bst.remove("0");
        assertEquals(0, bst.size());

        bst.put("0", 0);
        assertEquals(1, bst.size());
        bst.put("1", 1);
        assertEquals(2, bst.size());
        bst.remove("1");
        assertEquals(1, bst.size());
        bst.remove("0");
        assertEquals(0, bst.size());

        bst.remove("2");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveWithNullKey() {
        bst.remove(null);
    }

    @Test
    public void testRemoveMin() {
        bst.put("0", 0);
        bst.put("1", 1);
        assertEquals(2, bst.size());
        bst.removeMin();
        assertEquals(1, bst.size());
        assertNull(bst.get("0"));
        assertEquals((Object) 1, bst.get("1"));
        bst.removeMin();
        assertEquals(0, bst.size());
        assertNull(bst.get("1"));
    }

    @Test(expected = NoSuchElementException.class)
    public void testRemoveMinOnEmptyTree() {
        bst.removeMin();
    }

    @Test
    public void testRemoveMax() {
        bst.put("0", 0);
        bst.put("1", 1);
        assertEquals(2, bst.size());
        bst.removeMax();
        assertEquals(1, bst.size());
        assertNull(bst.get("1"));
        assertEquals((Object) 0, bst.get("0"));
        bst.removeMax();
        assertEquals(0, bst.size());
        assertNull(bst.get("0"));
    }

    @Test(expected = NoSuchElementException.class)
    public void testRemoveMaxOnEmptyTree() {
        bst.removeMax();
    }

    @Test
    public void testIsEmpty() {
        assertTrue(bst.isEmpty());
        bst.put("null", null);
        assertTrue(bst.isEmpty());
        bst.put("0", 0);
        assertFalse(bst.isEmpty());
        bst.remove("0");
        assertTrue(bst.isEmpty());
    }

    @Test
    public void testSize() {
        assertEquals(0, bst.size());
        bst.put("null", null);
        assertEquals(0, bst.size());
        bst.put("0", 0);
        assertEquals(1, bst.size());
        bst.put("1", 1);
        assertEquals(2, bst.size());
        bst.remove("1");
        assertEquals(1, bst.size());
        bst.remove("0");
        assertEquals(0, bst.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSizeWithRangeWithNullArgument() {
        bst.size(null, null);
    }

    @Test
    public void testSizeWithRange() {
        assertEquals(0, bst.size("9", "0"));
        assertEquals(0, bst.size("0", "9"));

        bst.put("0", 0);
        assertEquals(1, bst.size("0", "9"));
        bst.put("9", 9);
        assertEquals(2, bst.size("0", "9"));
        bst.put("1", 1);
        assertEquals(3, bst.size("0", "9"));
        bst.put("#", Integer.MIN_VALUE);
        assertEquals(3, bst.size("0", "9"));
        assertEquals(2, bst.size("0", "8"));

        bst.remove("0");
        assertEquals(1, bst.size("0", "8"));
    }

    @Test
    public void testContains() {
        bst.put("null", null);
        assertFalse(bst.contains("null"));
        bst.put("0", 0);
        assertTrue(bst.contains("0"));
        bst.remove("0");
        assertFalse(bst.contains("0"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testContainsWithNullKey() {
        bst.contains(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPutWithNullKey() {
        bst.put(null, 0);
    }

    @Test
    public void testGet() {
        bst.put("null", null);
        assertEquals(0, bst.size());
        assertNull(bst.get("null"));

        bst.put("0", 0);
        assertEquals(1, bst.size());
        assertEquals((Object) 0, bst.get("0"));

        bst.put("1", 1);
        assertEquals(2, bst.size());
        assertEquals((Object) 1, bst.get("1"));

        assertNull(bst.get("2"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetWithNullKey() {
        bst.get(null);
    }

    @Test
    public void testMin() {
        bst.put("1", 1);
        assertEquals("1", bst.min());
        bst.put("0", 0);
        assertEquals("0", bst.min());
        bst.remove("0");
        assertEquals("1", bst.min());
    }

    @Test(expected = NoSuchElementException.class)
    public void testMinOnEmptyTree() {
        bst.min();
    }

    @Test
    public void testMax() {
        bst.put("0", 0);
        assertEquals("0", bst.max());
        bst.put("1", 1);
        assertEquals("1", bst.max());
        bst.remove("1");
        assertEquals("0", bst.max());
    }

    @Test(expected = NoSuchElementException.class)
    public void testMaxOnEmptyTree() {
        bst.max();
    }

    @Test
    public void testFloor() {
        bst.put("1", 1);
        assertNull(bst.floor("0"));
        assertEquals("1", bst.floor("1"));
        assertEquals("1", bst.floor("2"));

        bst.put("2", 2);
        assertNull(bst.floor("0"));
        assertEquals("1", bst.floor("1"));
        assertEquals("2", bst.floor("2"));
        assertEquals("2", bst.floor("3"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFloorWithNullKey() {
        bst.put("0", 0);
        bst.floor(null);
    }

    @Test(expected = NoSuchElementException.class)
    public void testFloorOnEmptyTree() {
        bst.floor("0");
    }

    @Test
    public void testCeiling() {
        bst.put("1", 1);
        assertEquals("1", bst.ceiling("0"));
        assertEquals("1", bst.ceiling("1"));
        assertNull(bst.ceiling("2"));

        bst.put("2", 2);
        assertEquals("1", bst.ceiling("0"));
        assertEquals("1", bst.ceiling("1"));
        assertEquals("2", bst.ceiling("2"));
        assertNull(bst.ceiling("3"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCeilingWithNullKey() {
        bst.put("0", 0);
        bst.ceiling(null);
    }

    @Test(expected = NoSuchElementException.class)
    public void testCeilingOnEmptyTree() {
        bst.ceiling("0");
    }

    @Test
    public void testSelect() {
        bst.put("0", 0);
        assertEquals("0", bst.select(0));

        bst.put("1", 1);
        assertEquals("0", bst.select(0));
        assertEquals("1", bst.select(1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSelectWithNegativeArgument() {
        bst.select(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSelectWithArgumentEqualToTreeSize() {
        bst.put("0", 0);
        bst.select(1);
    }

    @Test
    public void testRank() {
        assertEquals((Object) 0, bst.rank("0"));
        assertEquals((Object) 0, bst.rank("1"));
        assertEquals((Object) 0, bst.rank("2"));

        bst.put("0", 0);
        assertEquals((Object) 0, bst.rank("0"));
        assertEquals((Object) 1, bst.rank("1"));

        bst.put("1", 1);
        assertEquals((Object) 1, bst.rank("1"));
        assertEquals((Object) 2, bst.rank("2"));

        bst.remove("1");
        assertEquals((Object) 1, bst.rank("2"));

        bst.remove("0");
        assertEquals((Object) 0, bst.rank("1"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRankWithNullKey() {
        bst.rank(null);
    }

    @Test
    public void testKeys() {
        bst.put("0", 0);
        testIterable(bst.keys(), new String[] {"0"});
        bst.put("1", 1);
        testIterable(bst.keys(), new String[] {"0", "1"});
        bst.remove("0");
        testIterable(bst.keys(), new String[] {"1"});
    }

    @Test(expected = NoSuchElementException.class)
    public void testKeysOnEmptyTree() {
        bst.keys();
    }

    @Test
    public void testKeysWithRange() {
        bst.put("2", 2);
        bst.put("3", 3);
        bst.put("4", 4);

        testIterable(bst.keys("0", "1"));
        testIterable(bst.keys("5", "6"));

        testIterable(bst.keys("1", "0"));

        testIterable(bst.keys("0", "2"), new String[] {"2"});
        testIterable(bst.keys("4", "5"), new String[] {"4"});

        testIterable(bst.keys("3", "3"), new String[] {"3"});
        testIterable(bst.keys("2", "4"), new String[] {"2", "3", "4"});
        testIterable(bst.keys("2", "3"), new String[] {"2", "3"});
        testIterable(bst.keys("3", "4"), new String[] {"3", "4"});
        testIterable(bst.keys("1", "5"), new String[] {"2", "3", "4"});
    }

    @Test(expected = IllegalArgumentException.class)
    public void testKeysWithNullRange() {
        bst.keys(null, null);
    }

    @Test
    public void testOrderSymmetry() {
        fillTree();
        testOrderSymmetry(((SimpleBinarySearchTree<String, Integer>) bst).root, null, null);
    }

    @Test
    public void testSizeConsistency() {
        fillTree();
        testSizeConsistency(((SimpleBinarySearchTree<String, Integer>) bst).root);
    }

    @Test
    public void testRankConsistency() {
        fillTree();
        for (int i = 0; i < bst.size(); i++)
            assertFalse(i != bst.rank(bst.select(i)));
        for (String key : bst.keys())
            assertEquals(0, key.compareTo(bst.select(bst.rank(key))));
    }

    protected <T> void testIterable(Iterable<T> iterable) {
        testIterable(iterable, null);
    }

    protected <T> void testIterable(Iterable<T> iterable, T[] expectedValues) {
        assertNotNull(iterable);
        Iterator<T> iterator = iterable.iterator();
        assertNotNull(iterator);
        if (expectedValues == null || expectedValues.length == 0)
            assertFalse(iterator.hasNext());
        else {
            for (T v : expectedValues) {
                assertTrue(iterator.hasNext());
                assertEquals(v, iterator.next());
            }
            assertFalse(iterator.hasNext());
        }
    }

    protected void fillTree() {
        String s = "S E A R C H E X A M P L E";
        String[] letters = s.split(" ");
        for (int i = 0; i < letters.length; i++)
            bst.put(letters[i], i);
    }

    private void testOrderSymmetry(SimpleBinarySearchTree<String, Integer>.Node node, String minKey, String maxKey) {
        if (node == null)
            return;
        assertFalse(minKey != null && node.key.compareTo(minKey) <= 0);
        assertFalse(maxKey != null && node.key.compareTo(maxKey) >= 0);
        testOrderSymmetry(node.left, minKey, node.key);
        testOrderSymmetry(node.right, node.key, maxKey);
    }

    private void testSizeConsistency(SimpleBinarySearchTree<String, Integer>.Node node) {
        if (node == null)
            return;
        assertFalse(node.size != ((SimpleBinarySearchTree<String, Integer>) bst).size(node.left)
                + ((SimpleBinarySearchTree<String, Integer>) bst).size(node.right) + 1);
        testSizeConsistency(node.left);
        testSizeConsistency(node.right);
    }
}
