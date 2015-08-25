package com.foursking.algorithms.data_structures.test.common;

import java.util.Iterator;

/**
 * Created by Administrator on 2015/8/25.
 */

public class IteratorTest {

    public static <T extends Comparable<T>> boolean testIterator(Iterator<T> iter) {
        while (iter.hasNext()) {
            T item = iter.next();
            if (item == null) {
                System.err.println("Iterator failure.");
                return false;
            }
        }
        return true;
    }
}
