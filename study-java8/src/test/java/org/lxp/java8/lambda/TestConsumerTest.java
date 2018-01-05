package org.lxp.java8.lambda;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestConsumerTest {
    private TestConsumer testConsumer;

    @Before
    public void setUp() throws Exception {
        testConsumer = new TestConsumer();
    }

    @Test
    public void testExecuteInt() throws Exception {
        Assert.assertEquals(101, testConsumer.execute(100));
    }

    @Test
    public void testExecuteLambda() throws Exception {
        Assert.assertEquals(1001, testConsumer.execute(1000));
    }

}
