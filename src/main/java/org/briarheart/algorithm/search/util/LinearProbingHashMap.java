package org.briarheart.algorithm.search.util;

import org.briarheart.algorithm.util.Preconditions;

import java.util.*;

/**
 * <blockquote>
 * <p>
 * Linear probing is a scheme in computer programming for resolving collisions in hash tables, data structures for
 * maintaining a collection of key–value pairs and looking up the value associated with a given key. It was invented
 * in 1954 by Gene Amdahl, Elaine M. McGraw, and Arthur Samuel and first analyzed in 1963 by Donald Knuth.
 * </p>
 * <footer>
 * <cite>Wikipedia</cite>
 * </footer>
 * </blockquote>
 * <p>
 *
 * @author Roman Chigvintsev
 */
public class LinearProbingHashMap<K, V> implements Map<K, V> {
    private static final int DEFAULT_INITIAL_CAPACITY = 4;

    private K[] keys;
    private V[] values;
    private int size;
    private int capacity;

    public LinearProbingHashMap() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public LinearProbingHashMap(int initialCapacity) {
        Preconditions.notNegative(initialCapacity, "Initial capacity must not be negative");
        capacity = initialCapacity;
        keys = (K[]) new Object[initialCapacity];
        values = (V[]) new Object[initialCapacity];
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
        Preconditions.notNull(key, "Key must not be null");
        return get(key) != null;
    }

    @Override
    public boolean containsValue(Object value) {
        if (value == null || isEmpty()) {
            return false;
        }

        for (V v : values) {
            if (v != null && v.equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public V get(Object key) {
        Preconditions.notNull(key, "Key must not be null");
        if (isEmpty()) {
            return null;
        }
        int i = hash(key);
        while (keys[i] != null) {
            if (keys[i].equals(key)) {
                return values[i];
            }
            i = (i + 1) % capacity;
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        Preconditions.notNull(key,"Key must not be null");
        if (value == null) {
            return remove(key);
        }

        if (size >= capacity / 2) {
            resize(2 * capacity);
        }

        int i = hash(key);
        while (keys[i] != null) {
            if (keys[i].equals(key)) {
                V prevValue = values[i];
                values[i] = value;
                return prevValue;
            }
            i = (i + 1) % capacity;
        }

        keys[i] = key;
        values[i] = value;
        size++;
        return null;
    }

    @Override
    public V remove(Object key) {
        Preconditions.notNull(key, "Key must not be null");
        if (!containsKey(key)) {
            return null;
        }

        int i = hash(key);
        while (!key.equals(keys[i])) {
            i = (i + 1) % capacity;
        }

        keys[i] = null;
        V prevValue = values[i];
        values[i] = null;

        i = (i + 1) % capacity;
        while (keys[i] != null) {
            K  keyToRehash = keys[i];
            V valueToRehash = values[i];
            keys[i] = null;
            values[i] = null;
            size--;
            put(keyToRehash, valueToRehash);
            i = (i + 1) % capacity;
        }

        size--;

        if (size > 0 && size <= capacity / 8) {
            resize(capacity / 2);
        }

        return prevValue;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        Preconditions.notNull(m, "Map must not be null");
        for (Map.Entry<? extends K, ? extends V> e : m.entrySet()) {
            put(e.getKey(), e.getValue());
        }
    }

    @Override
    public void clear() {
        if (isEmpty()) {
            return;
        }
        for (int i = 0; i < capacity; i++) {
            keys[i] = null;
            values[i] = null;
        }
        size = 0;
    }

    @Override
    public Set<K> keySet() {
        if (isEmpty()) {
            return Collections.emptySet();
        }
        Set<K> keys = new LinkedHashSet<>();
        for (K key : this.keys) {
            if (key != null) {
                keys.add(key);
            }
        }
        return keys;
    }

    @Override
    public Collection<V> values() {
        if (isEmpty()) {
            return Collections.emptyList();
        }
        List<V> values = new ArrayList<>();
        for (V value : this.values) {
            if (value != null) {
                values.add(value);
            }
        }
        return values;
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        if (isEmpty()) {
            return Collections.emptySet();
        }
        Set<Map.Entry<K, V>> entries = new LinkedHashSet<>();
        for (int i = 0; i < capacity; i++) {
            if (keys[i] != null) {
                entries.add(new Entry<>(keys[i], values[i]));
            }
        }
        return entries;
    }

    private int hash(Object key) {
        return (key.hashCode() & 0x7fffffff) % capacity;
    }

    private void resize(int newCapacity) {
        LinearProbingHashMap<K, V> temp = new LinearProbingHashMap<>(newCapacity);
        for (int i = 0; i < capacity; i++) {
            if (keys[i] != null) {
                temp.put(keys[i], values[i]);
            }
        }
        keys = temp.keys;
        values = temp.values;
        capacity = temp.capacity;
    }

    private record Entry<K, V>(K key, V value) implements Map.Entry<K, V> {
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
            throw new UnsupportedOperationException("Map entry is unmodifiable");
        }
    }
}
