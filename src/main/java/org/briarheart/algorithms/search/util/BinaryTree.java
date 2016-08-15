package org.briarheart.algorithms.search.util;

/**
 * @author Roman Chigvintsev
 */
public interface BinaryTree<K, V> {
    boolean isEmpty();

    int size();

    boolean contains(K key);

    V get(K key);

    void put(K key, V value);

    void remove(K key);

    Iterable<K> keys();

    int height();

    Iterable<K> levelOrder();
}
