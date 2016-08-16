package org.briarheart.algorithms.search.util;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.*;

import static org.junit.Assert.*;

/**
 * @author Roman Chigvintsev
 */
public class BinarySearchTreeTest {
    private static final String TEST_DATA_FILE_NAME = "tinyST.txt";

    private static String[] searchExample;

    private BinarySearchTree<String, Integer> bst;
    private Map<String, Integer> checkMap;

    @BeforeClass
    public static void setUpClass() throws IOException {
        ClassLoader classLoader = BinarySearchTreeTest.class.getClassLoader();
        URL fileUrl = classLoader.getResource(TEST_DATA_FILE_NAME);
        if (fileUrl == null)
            throw new FileNotFoundException(TEST_DATA_FILE_NAME);
        Scanner scanner = new Scanner(fileUrl.openStream());
        List<String> strings = new ArrayList<>();
        while (scanner.hasNext())
            strings.add(scanner.next().trim());
        if (strings.isEmpty())
            throw new IllegalStateException("File " + TEST_DATA_FILE_NAME + " is empty");
        searchExample = strings.toArray(new String[strings.size()]);
    }

    @Before
    public void setUp() {
        bst = new BinarySearchTree<>();
        checkMap = new HashMap<>(searchExample.length);

        for (int i = 0; i < searchExample.length; i++) {
            bst.put(searchExample[i], i);
            checkMap.put(searchExample[i], i);
        }
    }

    @Test
    public void doTest() {
        assertFalse(bst.isEmpty());

        assertTrue("Not in symmetric order", isBinarySearchTree(bst));
        assertTrue("Subtree counts are not consistent", isSizeConsistent(bst));
        assertTrue("Ranks are not consistent", isRankConsistent(bst));

        assertEquals(checkMap.size(), bst.size());

        for (Map.Entry<String, Integer> entry : checkMap.entrySet()) {
            assertTrue(bst.contains(entry.getKey()));
            assertEquals(entry.getValue(), bst.get(entry.getKey()));
            bst.remove(entry.getKey());
            assertFalse(bst.contains(entry.getKey()));
            assertNull(bst.get(entry.getKey()));
        }
    }

    /*
     * Checks if the binary tree satisfies symmetric order.
     * This test also ensures that data structure is a binary tree since order is strict.
     */
    private boolean isBinarySearchTree(BinarySearchTree bst) {
        return isBinarySearchTree(bst.root, null, null);
    }

    @SuppressWarnings({"unchecked", "SimplifiableIfStatement"})
    private boolean isBinarySearchTree(BinarySearchTree.Node node, Comparable minKey, Comparable maxKey) {
        if (node == null)
            return true;
        if (minKey != null && node.key.compareTo(minKey) <= 0)
            return false;
        if (maxKey != null && node.key.compareTo(maxKey) >= 0)
            return false;
        return isBinarySearchTree(node.left, minKey, node.key) && isBinarySearchTree(node.right, node.key, maxKey);
    }

    private boolean isSizeConsistent(BinarySearchTree bst) {
        return isSizeConsistent(bst.root);
    }

    @SuppressWarnings({"unchecked", "SimplifiableIfStatement"})
    private boolean isSizeConsistent(BinarySearchTree.Node node) {
        if (node == null)
            return true;
        if (node.size != bst.size(node.left) + bst.size(node.right) + 1)
            return false;
        return isSizeConsistent(node.left) && isSizeConsistent(node.right);
    }

    @SuppressWarnings("unchecked")
    private boolean isRankConsistent(BinarySearchTree bst) {
        for (int i = 0; i < bst.size(); i++)
            if (i != bst.rank(bst.select(i)))
                return false;
        for (Object key : bst.keys()) {
            int keyRank = bst.rank((Comparable) key);
            if (((Comparable) key).compareTo(bst.select(keyRank)) != 0)
                return false;
        }
        return true;
    }
}
