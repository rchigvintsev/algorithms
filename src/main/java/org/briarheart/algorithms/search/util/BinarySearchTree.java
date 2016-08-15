package org.briarheart.algorithms.search.util;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * @author Roman Chigvintsev
 */
public class BinarySearchTree<K extends Comparable<? super K>, V> implements BinaryTree<K, V> {
    protected Node root;

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        return size(root);
    }

    public int size(K lo, K hi) {
        if (lo == null)
            throw new NullPointerException("Low key cannot be null");
        if (hi == null)
            throw new NullPointerException("High key cannot null");

        if (lo.compareTo(hi) > 0)
            return 0;

        if (contains(hi))
            return rank(hi) - rank(lo) + 1;
        return rank(hi) - rank(lo);
    }

    @Override
    public boolean contains(K key) {
        if (key == null)
            throw new NullPointerException("Key cannot be null");
        return get(key) != null;
    }

    @Override
    public V get(K key) {
        if (key == null)
            throw new NullPointerException("Key cannot be null");
        return get(root, key);
    }

    @Override
    public void put(K key, V value) {
        if (key == null)
            throw new NullPointerException("Key cannot be null");

        if (value == null) {
            remove(key);
            return;
        }

        root = put(root, key, value);
    }

    @Override
    public void remove(K key) {
        if (key == null)
            throw new NullPointerException("Key cannot be null");
        root = remove(root, key);
    }

    public void removeMin() {
        if (isEmpty())
            throw new NoSuchElementException("Tree is empty");
        root = removeMin(root);
    }

    public void removeMax() {
        if (isEmpty())
            throw new NoSuchElementException("Tree is empty");
        root = removeMax(root);
    }

    public K min() {
        if (isEmpty())
            throw new NoSuchElementException("Tree is empty");
        return min(root).key;
    }

    public K max() {
        if (isEmpty())
            throw new NoSuchElementException("Tree is empty");
        return max(root).key;
    }

    public K floor(K key) {
        if (key == null)
            throw new NullPointerException("Key cannot be null");
        if (isEmpty())
            throw new NoSuchElementException("Tree is empty");

        Node node = floor(root, key);
        return node == null ? null : node.key;
    }

    public K ceiling(K key) {
        if (key == null)
            throw new NullPointerException("Key cannot be null");
        if (isEmpty())
            throw new NoSuchElementException("Tree is empty");

        Node node = ceiling(root, key);
        return node == null ? null : node.key;
    }

    public K select(int k) {
        if (k < 0 || k >= size())
            throw new IllegalArgumentException("k cannot be less than zero or greater than size of the tree");
        return select(root, k).key;
    }

    public int rank(K key) {
        if (key == null)
            throw new NullPointerException("Key cannot be null");
        return rank(key, root);
    }

    @Override
    public Iterable<K> keys() {
        return keys(min(), max());
    }

    public Iterable<K> keys(K lo, K hi) {
        if (lo == null)
            throw new NullPointerException("Low key cannot be null");
        if (hi == null)
            throw new NullPointerException("High key cannot null");

        Queue<K> queue = new LinkedList<K>();
        keys(root, queue, lo, hi);
        return queue;
    }

    @Override
    public int height() {
        return height(root);
    }

    @Override
    public Iterable<K> levelOrder() {
        Queue<K> keys = new LinkedList<>();
        Queue<Node> nodes = new LinkedList<>();
        nodes.offer(root);

        while (!nodes.isEmpty()) {
            Node node = nodes.poll();
            if (node == null)
                continue;

            keys.offer(node.key);
            nodes.offer(node.left);
            nodes.offer(node.right);
        }

        return keys;
    }

    protected int size(Node node) {
        if (node == null)
            return 0;
        return node.size;
    }

    protected V get(Node node, K key) {
        while (node != null) {
            int cmp = key.compareTo(node.key);
            if (cmp < 0)
                node = node.left;
            else if (cmp > 0)
                node = node.right;
            else
                return node.value;
        }
        return null;
    }

    protected Node put(Node node, K key, V value) {
        if (node == null)
            return new Node(key, value, 1);

        int cmp = key.compareTo(node.key);
        if (cmp < 0)
            node.left = put(node.left,  key, value);
        else if (cmp > 0)
            node.right = put(node.right, key, value);
        else
            node.value = value;
        updateSize(node);
        return node;
    }

    protected Node remove(Node node, K key) {
        if (node == null)
            return null;

        int cmp = key.compareTo(node.key);
        if (cmp < 0)
            node.left  = remove(node.left,  key);
        else if (cmp > 0)
            node.right = remove(node.right, key);
        else {
            if (node.right == null)
                return node.left;
            if (node.left  == null)
                return node.right;
            Node t = node;
            node = min(t.right);
            node.right = removeMin(t.right);
            node.left = t.left;
        }
        updateSize(node);
        return node;
    }

    protected Node removeMin(Node node) {
        if (node.left == null)
            return node.right;
        node.left = removeMin(node.left);
        updateSize(node);
        return node;
    }

    protected Node removeMax(Node node) {
        if (node.right == null)
            return node.left;
        node.right = removeMax(node.right);
        updateSize(node);
        return node;
    }

    protected Node min(Node node) {
        if (node.left == null)
            return node;
        return min(node.left);
    }

    protected Node max(Node node) {
        if (node.right == null)
            return node;
        return max(node.right);
    }

    protected Node floor(Node node, K key) {
        if (node == null)
            return null;
        int cmp = key.compareTo(node.key);
        if (cmp == 0)
            return node;
        if (cmp < 0)
            return floor(node.left, key);
        Node t = floor(node.right, key);
        if (t != null)
            return t;
        return node;
    }

    protected Node ceiling(Node node, K key) {
        if (node == null)
            return null;
        int cmp = key.compareTo(node.key);
        if (cmp == 0)
            return node;
        if (cmp < 0) {
            Node t = ceiling(node.left, key);
            if (t != null)
                return t;
            return node;
        }
        return ceiling(node.right, key);
    }

    protected Node select(Node node, int k) {
        if (node == null)
            return null;
        int lSize = size(node.left);
        if (lSize > k)
            return select(node.left, k);
        if (lSize < k)
            return select(node.right, k - lSize - 1);
        return node;
    }

    protected int rank(K key, Node node) {
        if (node == null)
            return 0;
        int cmp = key.compareTo(node.key);
        if (cmp < 0)
            return rank(key, node.left);
        if (cmp > 0)
            return size(node.left) + rank(key, node.right) + 1;
        return size(node.left);
    }

    protected void keys(Node node, Queue<K> queue, K lo, K hi) {
        if (node == null)
            return;
        int loCmp = lo.compareTo(node.key);
        int hiCmp = hi.compareTo(node.key);
        if (loCmp < 0)
            keys(node.left, queue, lo, hi);
        if (loCmp <= 0 && hiCmp >= 0)
            queue.offer(node.key);
        if (hiCmp > 0)
            keys(node.right, queue, lo, hi);
    }

    protected int height(Node node) {
        if (node == null)
            return -1;
        return Math.max(height(node.left), height(node.right)) + 1;
    }

    protected void updateSize(Node node) {
        node.size = size(node.left) + size(node.right) + 1;
    }

    protected class Node {
        protected K key;
        protected V value;
        protected Node left;
        protected Node right;
        protected int size;

        public Node(K key, V value, int size) {
            this.key = key;
            this.value = value;
            this.size = size;
        }
    }
}
