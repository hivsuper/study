package org.lxp.powermock.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.lxp.powermock.PowerMockHelper;
import org.lxp.powermock.RealPowerMockService;
import org.mockito.MockedStatic;
import org.mockito.Mockito;


/**
 * https://frontbackend.com/java/how-to-mock-static-methods-with-mockito
 */
public class RealPowerMockServiceImplWithMockitoInlineTest {
    private RealPowerMockService realPowerMockService;

    @Before
    public void setUp() {
        realPowerMockService = new RealPowerMockServiceImpl();
    }

    @Test
    public void testExecute() {
        Assert.assertEquals("Super Liaa", realPowerMockService.execute("aa"));
        try (MockedStatic<PowerMockHelper> theMock = Mockito.mockStatic(PowerMockHelper.class)) {
            theMock.when(PowerMockHelper::getName).thenReturn("bb");

            Assert.assertEquals("bb", PowerMockHelper.getName());

            Assert.assertEquals("bbaa", realPowerMockService.execute("aa"));
        }
        Assert.assertEquals("Super Liaa", realPowerMockService.execute("aa"));
    }

}
