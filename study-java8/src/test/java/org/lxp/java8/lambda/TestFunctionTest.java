package org.lxp.java8.lambda;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestFunctionTest {
    private TestFunction testFunction;

    @Before
    public void setUp() throws Exception {
        testFunction = new TestFunction();
    }

    @Test
    public void testExecuteString() throws Exception {
        Assert.assertEquals(100, testFunction.execute("100"));
    }

    @Test
    public void testExecuteLambda() throws Exception {
        Assert.assertEquals(90, testFunction.executeLambda("90"));
    }

    @Test(expected = NumberFormatException.class)
    public void throwNumberFormatException() throws Exception {
        Assert.assertEquals(90, testFunction.executeLambda("90.11"));
    }

}
