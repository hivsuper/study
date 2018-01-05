package org.lxp.java8.lambda;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestBiConsumerTest {
    private TestBiConsumer testBiConsumer;

    @Before
    public void setUp() throws Exception {
        testBiConsumer = new TestBiConsumer();
    }

    @Test
    public void testExecuteIntString() throws Exception {
        Assert.assertEquals("s1", testBiConsumer.execute(1, "s"));
    }

    @Test
    public void testExecuteLambda() throws Exception {
        Assert.assertEquals("s2", testBiConsumer.executeLambda(2, "s"));
    }

}
