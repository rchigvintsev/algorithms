package org.briarheart.algorithms.search.util;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Roman Chigvintsev
 */
public class RedBlackBinarySearchTreeTest extends AbstractBinarySearchTreeTest {
    @Before
    public void setUp() {
        bst = new RedBlackBinarySearchTree<>();
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
        assertEquals(1, bst.height());
        bst.remove("3");
        assertEquals(1, bst.height());
    }

    @Test
    public void test23Tree() {
        fillTree();
        test23Tree(((RedBlackBinarySearchTree<String, Integer>) bst).root);
    }

    @Test
    public void testBalance() {
        fillTree();
        int black = 0;
        RedBlackBinarySearchTree<String, Integer> redBlackBst = (RedBlackBinarySearchTree<String, Integer>) bst;
        SimpleBinarySearchTree<String, Integer>.Node node = redBlackBst.root;
        while (node != null) {
            if (!redBlackBst.isRedNode(node))
                black++;
            node = node.left;
        }
        testBalance(redBlackBst.root, black);
    }

    private void test23Tree(SimpleBinarySearchTree<String, Integer>.Node node) {
        RedBlackBinarySearchTree<String, Integer> redBlackBst = (RedBlackBinarySearchTree<String, Integer>) bst;
        if (node == null)
            return;
        assertFalse(redBlackBst.isRedNode(node.right));
        assertFalse(node != redBlackBst.root && redBlackBst.isRedNode(node) && redBlackBst.isRedNode(node.left));
        test23Tree(node.left);
        test23Tree(node.right);
    }

    private void testBalance(SimpleBinarySearchTree<String, Integer>.Node node, int black) {
        if (node == null) {
            assertTrue(black == 0);
            return;
        }
        RedBlackBinarySearchTree<String, Integer> redBlackBst = (RedBlackBinarySearchTree<String, Integer>) bst;
        if (!redBlackBst.isRedNode(node))
            black--;
        testBalance(node.left, black);
        testBalance(node.right, black);
    }
}
