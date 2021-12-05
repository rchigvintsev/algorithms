package org.briarheart.test.util;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Roman Chigvintsev
 */
public class AssertUtils {
    private AssertUtils() {
        //no instance
    }

    public static <T> void assertIterableEmpty(Iterable<T> iterable) {
        assertIterableContainsOnly(null, iterable);
    }

    public static <T> void assertIterableContainsOnly(T[] expectedValues, Iterable<T> iterable) {
        assertNotNull(iterable);
        Iterator<T> iterator = iterable.iterator();
        assertNotNull(iterator);
        if (expectedValues == null || expectedValues.length == 0)
            assertFalse(iterator.hasNext());
        else {
            for (T v : expectedValues) {
                assertTrue(iterator.hasNext());
                assertEquals(v, iterator.next());
            }
            assertFalse(iterator.hasNext());
        }
    }
}
