package org.briarheart.algorithms.search.impl;

/**
 * @author Roman Chigvintsev
 */
class BinarySearch<T extends Comparable<? super T>> extends AbstractSearchEngine<T> {
    @Override
    public int doSearch(T key, T[] a) {
        if (key == null)
            throw new IllegalArgumentException("Key cannot be null");
        if (a == null)
            throw new IllegalArgumentException("Array cannot be null");
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int r = key.compareTo(a[mid]);
            if (r < 0)
                hi = mid - 1;
            else if (r > 0)
                lo = mid + 1;
            else
                return mid;
        }
        return -1;
    }
}
