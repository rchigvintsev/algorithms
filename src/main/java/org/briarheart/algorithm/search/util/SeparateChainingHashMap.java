package org.briarheart.algorithm.search.util;

import java.util.*;

/**
 * @author Roman Chigvintsev
 */
public class SeparateChainingHashMap<K, V> implements Map<K, V> {
    private static final int DEFAULT_INITIAL_CHAINS_NUMBER = 4;

    private int size;
    private Node<K, V>[] nodes;

    public SeparateChainingHashMap() {
        this(DEFAULT_INITIAL_CHAINS_NUMBER);
    }

    public SeparateChainingHashMap(int initialChainsNumber) {
        //noinspection unchecked
        nodes = new Node[initialChainsNumber];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        if (key == null)
            throw new IllegalArgumentException("Key cannot be null");
        return get(key) != null;
    }

    @Override
    public boolean containsValue(Object value) {
        if (value == null || isEmpty())
            return false;
        for (Node node : nodes)
            while (node != null) {
                if (value.equals(node.value))
                    return true;
                node = node.next;
            }
        return false;
    }

    @Override
    public V get(Object key) {
        if (key == null)
            throw new IllegalArgumentException("Key cannot be null");
        if (!isEmpty()) {
            int i = hash(key);
            Node<K, V> node = nodes[i];
            while (node != null) {
                if (key.equals(node.key))
                    return node.value;
                node = node.next;
            }
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        if (key == null)
            throw new IllegalArgumentException("Key cannot be null");
        if (value == null)
            return remove(key);
        if (size >= 10 * nodes.length)
            resize(2 * nodes.length);
        int i = hash(key);
        Node<K, V> node = nodes[i];
        while (node != null) {
            if (key.equals(node.key)) {
                V prevValue = node.value;
                node.value = value;
                return prevValue;
            }
            node = node.next;
        }
        nodes[i] = new Node<>(key, value, nodes[i]);
        size++;
        return null;
    }

    @Override
    public V remove(Object key) {
        if (key == null)
            throw new IllegalArgumentException("Key cannot be null");
        if (!isEmpty()) {
            int i = hash(key);
            Node<K, V> prevNode = null, node = nodes[i];
            while (node != null) {
                if (key.equals(node.key)) {
                    if (prevNode == null)
                        nodes[i] = node.next;
                    else
                        prevNode.next = node.next;
                    size--;
                    if (nodes.length > DEFAULT_INITIAL_CHAINS_NUMBER && size <= 2 * nodes.length)
                        resize(nodes.length / 2);
                    return node.value;
                }
                prevNode = node;
                node = node.next;
            }
        }
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        if (m == null)
            throw new IllegalArgumentException("Map cannot be null");
        for (Map.Entry<? extends K, ? extends V> e : m.entrySet())
            put(e.getKey(), e.getValue());
    }

    @Override
    public void clear() {
        if (isEmpty())
            return;

        for (int i = 0; i < nodes.length; i++)
            nodes[i] = null;
        size = 0;
    }

    @Override
    public Set<K> keySet() {
        if (isEmpty())
            return Collections.emptySet();

        Set<K> keys = new LinkedHashSet<>();
        for (Node<K, V> node : nodes)
            while (node != null) {
                keys.add(node.key);
                node = node.next;
            }
        return keys;

    }

    @Override
    public Collection<V> values() {
        if (isEmpty())
            return Collections.emptyList();

        List<V> values = new ArrayList<>();
        for (Node<K, V> node : nodes)
            while (node != null) {
                values.add(node.value);
                node = node.next;
            }
        return values;
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        if (isEmpty())
            return Collections.emptySet();
        Set<Map.Entry<K, V>> entries = new LinkedHashSet<>();
        for (Node<K, V> node : this.nodes)
            while (node != null) {
                entries.add(node);
                node = node.next;
            }
        return entries;
    }

    private void resize(int newChainsNumber) {
        SeparateChainingHashMap<K, V> temp = new SeparateChainingHashMap<>(newChainsNumber);
        for (Node<K, V> node : nodes)
            while (node != null) {
                temp.put(node.key, node.value);
                node = node.next;
            }

        this.nodes = temp.nodes;
    }

    private int hash(Object key) {
        return (key.hashCode() & 0x7fffffff) % nodes.length;
    }

    private static class Node<K, V> implements Map.Entry<K, V> {
        K key;
        V value;
        Node<K, V> next;

        Node(K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            V prevValue = this.value;
            this.value = value;
            return prevValue;
        }

        @Override
        public String toString() {
            return "[" + key + " = " + value + "]";
        }
    }
}
