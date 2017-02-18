package org.briarheart.algorithms.search.util;

import java.util.NoSuchElementException;

/**
 * @author Roman Chigvintsev
 */
public class RedBlackBinarySearchTree<K extends Comparable<? super K>, V> extends SimpleBinarySearchTree<K, V> {
    @Override
    public void put(K key, V value) {
        super.put(key, value);
        if (!isEmpty())
            ((RedBlackNode) root).color = NodeColor.BLACK;
    }

    @Override
    public void remove(K key) {
        if (key == null)
            throw new IllegalArgumentException("Key cannot be null");
        if (!contains(key))
            return;
        if (!isRedNode(root.left) && !isRedNode(root.right))
            ((RedBlackNode) root).color = NodeColor.RED;
        root = remove(root, key);
        if (!isEmpty())
            ((RedBlackNode) root).color = NodeColor.BLACK;
    }

    @Override
    public void removeMin() {
        if (isEmpty())
            throw new NoSuchElementException("Tree is empty");
        if (!isRedNode(root.left) && !isRedNode(root.right))
            ((RedBlackNode) root).color = NodeColor.RED;
        root = removeMin(root);
        if (!isEmpty())
            ((RedBlackNode) root).color = NodeColor.BLACK;
    }

    @Override
    public void removeMax() {
        if (isEmpty())
            throw new NoSuchElementException("Tree is empty");
        if (!isRedNode(root.left) && !isRedNode(root.right))
            ((RedBlackNode) root).color = NodeColor.RED;
        root = removeMax(root);
        if (!isEmpty())
            ((RedBlackNode) root).color = NodeColor.BLACK;
    }

    @Override
    protected Node<K, V> put(Node<K, V> node, K key, V value) {
        if (node == null)
            return new RedBlackNode<>(key, value, NodeColor.RED);
        node = super.put(node, key, value);
        if (isRedNode(node.right) && !isRedNode(node.left))
            node = rotateLeft(node);
        if (isRedNode(node.left) && isRedNode(node.left.left))
            node = rotateRight(node);
        if (isRedNode(node.left) && isRedNode(node.right))
            flipNodeColors(node);
        updateSize(node);
        return node;
    }

    @Override
    protected Node<K, V> remove(Node<K, V> node, K key) {
        if (key.compareTo(node.key) < 0)  {
            if (!isRedNode(node.left) && !isRedNode(node.left.left))
                node = moveRedLeft(node);
            node.left = remove(node.left, key);
        } else {
            if (isRedNode(node.left))
                node = rotateRight(node);
            if (key.compareTo(node.key) == 0 && node.right == null)
                return null;
            if (!isRedNode(node.right) && !isRedNode(node.right.left))
                node = moveRedRight(node);
            if (key.compareTo(node.key) == 0) {
                Node<K, V> minRight = min(node.right);
                node.key = minRight.key;
                node.value = minRight.value;
                node.right = removeMin(node.right);
            } else
                node.right = remove(node.right, key);
        }
        return balance(node);
    }

    @Override
    protected Node<K, V> removeMin(Node<K, V> node) {
        if (node.left == null)
            return null;
        if (!isRedNode(node.left) && !isRedNode(node.left.left))
            node = moveRedLeft(node);
        node.left = removeMin(node.left);
        return balance(node);
    }

    @Override
    protected Node<K, V> removeMax(Node<K, V> node) {
        if (isRedNode(node.left))
            node = rotateRight(node);
        if (node.right == null)
            return null;
        if (!isRedNode(node.right) && !isRedNode(node.right.left))
            node = moveRedRight(node);
        node.right = removeMax(node.right);
        return balance(node);
    }

    protected boolean isRedNode(Node node) {
        return node != null && ((RedBlackNode) node).color == NodeColor.RED;
    }

    private Node<K, V> rotateLeft(Node<K, V> node) {
        Node<K, V> tmp = node;
        node = node.right;
        tmp.right = node.left;
        node.left = tmp;
        ((RedBlackNode) node).color = ((RedBlackNode) node.left).color;
        ((RedBlackNode) node.left).color = NodeColor.RED;
        node.size = node.left.size;
        updateSize(node.left);
        return node;
    }

    private Node<K, V> rotateRight(Node<K, V> node) {
        Node<K, V> tmp = node;
        node = node.left;
        tmp.left = node.right;
        node.right = tmp;
        ((RedBlackNode) node).color = ((RedBlackNode) node.right).color;
        ((RedBlackNode) node.right).color = NodeColor.RED;
        node.size = node.right.size;
        updateSize(node.right);
        return node;
    }

    private void flipNodeColors(Node<K, V> node) {
        flipNodeColor(node);
        flipNodeColor(node.left);
        flipNodeColor(node.right);
    }

    private void flipNodeColor(Node<K, V> node) {
        ((RedBlackNode) node).color = ((RedBlackNode) node).color == NodeColor.RED ? NodeColor.BLACK : NodeColor.RED;
    }

    /**
     * Assuming that {@code node} is red and both {@code node.left} and {@code node.left.left} are black,
     * make {@code node.left} or one of its children red.
     */
    private Node<K, V> moveRedLeft(Node<K, V> node) {
        flipNodeColors(node);
        if (isRedNode(node.right.left)) {
            node.right = rotateRight(node.right);
            node = rotateLeft(node);
            flipNodeColors(node);
        }
        return node;
    }

    private Node<K, V> moveRedRight(Node<K, V> node) {
        flipNodeColors(node);
        if (isRedNode(node.left.left)) {
            node = rotateRight(node);
            flipNodeColors(node);
        }
        return node;
    }

    private Node<K, V> balance(Node<K, V> node) {
        if (isRedNode(node.right))
            node = rotateLeft(node);
        if (isRedNode(node.left) && isRedNode(node.left.left))
            node = rotateRight(node);
        if (isRedNode(node.left) && isRedNode(node.right))
            flipNodeColors(node);
        updateSize(node);
        return node;
    }

    protected static class RedBlackNode<K, V> extends SimpleBinarySearchTree.Node<K, V> {
        protected NodeColor color;

        public RedBlackNode(K key, V value, NodeColor color) {
            this(key, value, color, 1);
        }

        public RedBlackNode(K key, V value, NodeColor color, int size) {
            super(key, value, size);
            this.color = color;
        }

        @Override
        public String toString() {
            return "[" + key + ": " + value + "] " + color;
        }
    }

    protected enum NodeColor {RED, BLACK}
}
