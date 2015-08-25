package com.foursking.algorithms.data_structures.test;

import static org.junit.Assert.assertTrue;
import com.foursking.algorithms.data_structures.List;
import com.foursking.algorithms.data_structures.test.common.JavaCollectionTest;
import com.foursking.algorithms.data_structures.test.common.ListTest;
import org.junit.Test;

import com.foursking.algorithms.data_structures.test.common.Utils;
import com.foursking.algorithms.data_structures.test.common.Utils.TestData;

import java.util.Collection;

public class ListTests {

    @Test
    public void testArrayList() {
        TestData data = Utils.generateTestData(1000);

        String aName = "List [array]";
        List.ArrayList<Integer> aList = new List.ArrayList<Integer>();
        Collection<Integer> aCollection = aList.toCollection();

        assertTrue(ListTest.testList(aList, aName, data.unsorted, data.invalid));
        assertTrue(JavaCollectionTest.testCollection(aCollection, Integer.class, aName,
                data.unsorted, data.sorted, data.invalid));
    }

    @Test
    public void testLinkedList() {
        TestData data = Utils.generateTestData(1000);
        String lName = "List [linked]";
        List.LinkedList<Integer> lList = new List.LinkedList<Integer>();
        Collection<Integer> lCollection = lList.toCollection();

        assertTrue(ListTest.testList(lList, lName,
                data.unsorted, data.invalid));
        assertTrue(JavaCollectionTest.testCollection(lCollection, Integer.class, lName,
                data.unsorted, data.sorted, data.invalid));
        //System.out.println(lName);
    }

}
