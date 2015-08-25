package com.foursking.algorithms.data_structures.test.common;

import java.util.ListIterator;
import java.util.NoSuchElementException;

public class ListIteratorTest {

    public static <T extends Comparable<T>> boolean testListIterator(ListIterator<T> iter, Class<T> type,
                                                                     Integer[] data, int size) {
        boolean exceptionThrown = false;
        try {
            iter.previous();
        } catch (NoSuchElementException e) {
            exceptionThrown = true;
        }

        if (!exceptionThrown) {
            System.err.println("ListIterator exception failure.");
            return false;
        }

        for (int i = 0; i < data.length; i++) {
            Integer value = data[i];
            T item = Utils.parseT(value, type);
            iter.add(item);
        }

        while (iter.hasPrevious()) iter.previous();

        int i=0;
        while (iter.hasNext()) {
            T item = iter.next();
            int idx = iter.nextIndex();
            if (idx!=++i) {
                System.err.println("ListIterator index failure.");
                return false;
            }
            if (item==null) {
                System.err.println("ListIterator item is null.");
                return false;
            }
        }

        exceptionThrown = false;
        try {
            iter.next();
        } catch (NoSuchElementException e) {
            exceptionThrown = true;
        }
        if (!exceptionThrown) {
            System.err.println("ListIterator exception failure.");
            return false;
        }

        iter.nextIndex();
        int listSize = iter.nextIndex();
        if (listSize!=size) {
            System.err.println("ListIterator ARRAY_SIZE failure.");
            return false;
        }
        return true;


    }
}
