package org.briarheart.test.util;

import java.util.Iterator;

import static org.junit.Assert.*;

/**
 * @author Roman Chigvintsev
 */
public class TestUtils {
    private TestUtils() {
        //no instance
    }

    public static <T> void testIterable(Iterable<T> iterable) {
        testIterable(iterable, null);
    }

    public static <T> void testIterable(Iterable<T> iterable, T[] expectedValues) {
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
