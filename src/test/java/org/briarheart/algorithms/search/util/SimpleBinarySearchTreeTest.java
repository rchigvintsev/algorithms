package org.briarheart.algorithms.search.util;

import org.junit.Before;
import org.junit.Test;

import static org.briarheart.test.util.TestUtils.testIterable;
import static org.junit.Assert.assertEquals;

/**
 * @author Roman Chigvintsev
 */
public class SimpleBinarySearchTreeTest extends AbstractBinarySearchTreeTest {
    @Before
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
        testIterable(simpleBst.levelOrder());
        bst.put("2", 2);
        bst.put("3", 3);
        bst.put("4", 4);
        testIterable(simpleBst.levelOrder(), new String[] {"2", "3", "4"});
        bst.put("0", 0);
        testIterable(simpleBst.levelOrder(), new String[] {"2", "0", "3", "4"});
        bst.put("1", 1);
        testIterable(simpleBst.levelOrder(), new String[] {"2", "0", "3", "1", "4"});
        bst.remove("2");
        testIterable(simpleBst.levelOrder(), new String[] {"3", "0", "4", "1"});
    }
}