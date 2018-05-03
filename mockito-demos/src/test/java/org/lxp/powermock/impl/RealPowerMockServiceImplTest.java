package org.lxp.powermock.impl;

import static org.lxp.powermock.PowerMockIgnoreHelper.POWER_MOCK_IGNORE_MANAGEMENT;
import static org.lxp.powermock.PowerMockIgnoreHelper.POWER_MOCK_IGNORE_SSL;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.lxp.powermock.PowerMockHelper;
import org.lxp.powermock.RealPowerMockService;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * Powermock 1.7.3 incompatible with Mockito > 2.8.47
 * 
 * @see https://github.com/powermock/powermock/issues/867
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ PowerMockHelper.class })
@PowerMockIgnore({ POWER_MOCK_IGNORE_MANAGEMENT, POWER_MOCK_IGNORE_SSL })
public class RealPowerMockServiceImplTest {
    private RealPowerMockService realPowerMockService;

    @Before
    public void setUp() throws Exception {
        realPowerMockService = new RealPowerMockServiceImpl();
    }

    @Test
    public void testExecute() throws Exception {
        Assert.assertEquals("Super Liaa", realPowerMockService.execute("aa"));

        PowerMockito.mockStatic(PowerMockHelper.class);
        PowerMockito.when(PowerMockHelper.getName()).thenReturn("bb");
        Assert.assertEquals("bbaa", realPowerMockService.execute("aa"));
    }

}