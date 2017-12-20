package org.lxp.mock.captor.impl;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.lxp.mock.captor.CaptorModel;
import org.lxp.mock.captor.CaptorService;
import org.lxp.mock.captor.RealCaptorService;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RealCaptorServiceImplTest {
    @Mock
    private CaptorService captorService;
    @Captor
    private ArgumentCaptor<List<CaptorModel>> argumentCaptor;
    private RealCaptorService realCaptorService;

    @Before
    public void setUp() throws Exception {
        realCaptorService = new RealCaptorServiceImpl(captorService);
    }

    @Test
    public void argumentCaptor() throws Exception {
        Mockito.doNothing().when(captorService).execute(argumentCaptor.capture());
        String propertyString = "propertyString";
        int propertyInt = 1;
        realCaptorService.batchExecute(propertyString, propertyInt);
        Assert.assertEquals("[CaptorModel [property1=propertyString, property2=1]]",
                argumentCaptor.getValue().toString());
    }

    @Test
    public void verifyAndAny() throws Exception {
        Mockito.doReturn("success").when(captorService).execute(Mockito.any(CaptorModel.class));
        realCaptorService.execute("propertyString", 1);
        Mockito.verify(captorService, Mockito.times(1)).execute(Mockito.any(CaptorModel.class));
    }

    @Test
    public void verifyAndEq() throws Exception {
        CaptorModel captorModel = new CaptorModel("propertyString", 1);
        Mockito.when(captorService.execute(captorModel)).thenReturn("lalala");
        realCaptorService.execute(captorModel);
        Mockito.verify(captorService, Mockito.times(1)).execute(Mockito.eq(captorModel));
    }

    @Test(expected = IllegalArgumentException.class)
    public void doThrow() {
        CaptorModel captorModel = new CaptorModel("propertyString", 1);
        Mockito.doThrow(new IllegalArgumentException("s")).when(captorService).execute(captorModel);
        realCaptorService.execute(captorModel);
    }

}
