package org.lxp.mock.service.inject.mock.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.lxp.mock.service.inject.mock.InjectService;
import org.lxp.mock.service.inject.mock.RealService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RealServiceImplTest {
    @Mock
    private InjectService injectService;
    @InjectMocks
    private RealService realService = new RealServiceImpl();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        Mockito.when(injectService.getName()).thenReturn("Super Li");
    }

    @Test
    public void testGetName() throws Exception {
        Assert.assertEquals("Real Name is Super Li", realService.getName());
    }

}
