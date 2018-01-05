package org.lxp.java8.lambda;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestSupplierTest {
    private TestSupplier testSupplier;

    @Before
    public void setUp() throws Exception {
        testSupplier = new TestSupplier();
    }

    @Test
    public void testExecute() throws Exception {
        Assert.assertEquals(100, testSupplier.execute());
    }

    @Test
    public void testExecuteLambda() throws Exception {
        Assert.assertEquals(100, testSupplier.executeLambda());
    }

}
