package org.briarheart.algorithms.search.util;

/**
 * @author Roman Chigvintsev
 */
public interface BinarySearchTree<K, V> {
    boolean isEmpty();

    int size();

    int size(K lo, K hi);

    int height();

    boolean contains(K key);

    V get(K key);

    void put(K key, V value);

    void remove(K key);

    void removeMin();

    void removeMax();

    K min();

    K max();

    K floor(K key);

    K ceiling(K key);

    K select(int k);

    int rank(K key);

    Iterable<K> keys();

    Iterable<K> keys(K lo, K hi);

    Iterable<K> levelOrder();
}
