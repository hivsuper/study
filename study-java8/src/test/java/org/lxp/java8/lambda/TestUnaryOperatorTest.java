package org.lxp.java8.lambda;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestUnaryOperatorTest {
    private TestUnaryOperator testUnaryOperator;
    private List<Integer> list;

    @Before
    public void setUp() throws Exception {
        testUnaryOperator = new TestUnaryOperator();
        list = Arrays.asList(new Integer[] { 1, 2, 3, 4 });
    }

    @Test
    public void testExecute() throws Exception {
        testUnaryOperator.execute(list);
        Assert.assertEquals("[101, 102, 103, 104]", list.toString());
    }

    @Test
    public void testExecuteLambda() throws Exception {
        testUnaryOperator.executeLambda(list);
        Assert.assertEquals("[101, 102, 103, 104]", list.toString());
    }

}
