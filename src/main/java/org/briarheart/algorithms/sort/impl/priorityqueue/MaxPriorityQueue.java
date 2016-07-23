package org.briarheart.algorithms.sort.impl.priorityqueue;

import java.util.Comparator;
import java.util.Iterator;

/**
 * @author Roman Chigvintsev
 */
public class MaxPriorityQueue<T> implements Iterable<T> {
    private T[] queue;
    private int size;
    private Comparator<T> comparator;

    public MaxPriorityQueue() {
        this(1, null);
    }

    public MaxPriorityQueue(int initCapacity) {
        this(initCapacity, null);
    }

    public MaxPriorityQueue(Comparator<T> comparator) {
        this(1, comparator);
    }

    public MaxPriorityQueue(int initCapacity, Comparator<T> comparator) {
        //noinspection unchecked
        queue = (T[]) new Object[initCapacity + 1];
        this.comparator = comparator;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }
}
