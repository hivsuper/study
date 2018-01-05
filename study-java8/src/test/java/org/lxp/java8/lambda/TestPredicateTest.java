package org.lxp.java8.lambda;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestPredicateTest {
    private TestPredicate testPredicate;
    private List<Integer> list;

    @Before
    public void setUp() throws Exception {
        testPredicate = new TestPredicate();
        list = new ArrayList<>();
    }

    @Test
    public void testExecute() throws Exception {
        int[] array = { 1, 2, 3, 100, 124, 4 };
        for (int element : array) {
            list.add(element);
        }
        testPredicate.execute(list);
        Assert.assertEquals("[1, 2, 3, 100, 4]", list.toString());
    }

    @Test
    public void testExecuteLambda() throws Exception {
        int[] array = { 122, 2, 113, 100, 124, 4 };
        for (int element : array) {
            list.add(element);
        }
        testPredicate.executeLambda(list);
        Assert.assertEquals("[2, 100, 4]", list.toString());
    }

}
