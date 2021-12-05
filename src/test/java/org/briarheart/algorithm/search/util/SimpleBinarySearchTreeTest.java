package org.briarheart.algorithm.search.util;

import org.briarheart.test.util.AssertUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.briarheart.test.util.AssertUtils.assertIterableEmpty;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Roman Chigvintsev
 */
public class SimpleBinarySearchTreeTest extends AbstractBinarySearchTreeTest {
    @BeforeEach
    public void setUp() {
        bst = new SimpleBinarySearchTree<>();
    }

    @Test
    public void testHeight() {
        assertEquals(-1, bst.height());

        bst.put("1", 1);
        assertEquals(0, bst.height());
        bst.put("2", 2);
        assertEquals(1, bst.height());
        bst.put("0", 0);
        assertEquals(1, bst.height());
        bst.put("3", 3);
        assertEquals(2, bst.height());
        bst.put("2", 2);
        assertEquals(2, bst.height());

        bst.remove("0");
        assertEquals(2, bst.height());
        bst.remove("3");
        assertEquals(1, bst.height());
    }

    @Test
    public void testLevelOrder() {
        SimpleBinarySearchTree<String, Integer> simpleBst = (SimpleBinarySearchTree<String, Integer>) bst;
        assertIterableEmpty(simpleBst.levelOrder());
        bst.put("2", 2);
        bst.put("3", 3);
        bst.put("4", 4);
        AssertUtils.assertIterableContainsOnly(new String[] {"2", "3", "4"}, simpleBst.levelOrder());
        bst.put("0", 0);
        AssertUtils.assertIterableContainsOnly(new String[] {"2", "0", "3", "4"}, simpleBst.levelOrder());
        bst.put("1", 1);
        AssertUtils.assertIterableContainsOnly(new String[] {"2", "0", "3", "1", "4"}, simpleBst.levelOrder());
        bst.remove("2");
        AssertUtils.assertIterableContainsOnly(new String[] {"3", "0", "4", "1"}, simpleBst.levelOrder());
    }
}