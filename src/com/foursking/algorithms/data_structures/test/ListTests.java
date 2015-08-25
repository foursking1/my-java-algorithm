package com.foursking.algorithms.data_structures.test;

import static org.junit.Assert.assertTrue;
import com.foursking.algorithms.data_structures.List;
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

        assertTrue(ListTest.testList(aList,  aName, data.unsorted, data.invalid));
    }

}
