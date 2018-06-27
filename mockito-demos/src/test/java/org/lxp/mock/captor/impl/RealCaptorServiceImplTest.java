package org.lxp.mock.captor.impl;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import org.awaitility.Awaitility;
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
    public void setUp() {
        realCaptorService = new RealCaptorServiceImpl(captorService);
    }

    @Test
    public void argumentCaptor() {
        Mockito.doNothing().when(captorService).execute(argumentCaptor.capture());
        String propertyString = "propertyString";
        int propertyInt = 1;
        realCaptorService.batchExecute(propertyString, propertyInt);
        Assert.assertEquals("[CaptorModel [property1=propertyString, property2=1]]",
                argumentCaptor.getValue().toString());
    }

    @Test
    public void verifyAndAny() {
        Mockito.doReturn("success").when(captorService).execute(Mockito.any(CaptorModel.class));
        realCaptorService.execute("propertyString", 1);
        Mockito.verify(captorService, Mockito.times(1)).execute(Mockito.any(CaptorModel.class));
    }

    @Test
    public void verifyAndEq() {
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

    @Test
    public void doAnswer() {
        AtomicBoolean running = new AtomicBoolean(false);
        CaptorModel captorModel = new CaptorModel("propertyString", 1);
        Mockito.doAnswer(a -> {
            running.set(true);
            return Collections.emptyList();
        }).when(captorService).execute(captorModel);
        realCaptorService.asyncExecute(captorModel);
        Awaitility.await().untilTrue(running);
        Mockito.verify(captorService).execute(Mockito.eq(captorModel));
    }

    @Test
    public void returnDifferentValueInMultiCalls() {
        Mockito.doReturn("1", "a", "b").when(captorService).execute(Mockito.any(CaptorModel.class));
        List<String> rtn = realCaptorService.execute(4);
        Mockito.verify(captorService, Mockito.times(3)).execute(Mockito.any(CaptorModel.class));
        Assert.assertEquals("[1, a, b]", rtn.toString());
    }

    @Test
    public void spy() {
        // 测试模拟
        RealCaptorService spy = Mockito.spy(new RealCaptorServiceImpl(captorService));
        Mockito.doReturn(Collections.singletonList("1")).when(spy).execute(Mockito.eq(4));
        Assert.assertEquals("[1]", spy.execute(4).toString());

        // 测试真实调用
        Mockito.doReturn("1", "a").when(captorService).execute(Mockito.any(CaptorModel.class));
        List<String> rtn = spy.execute(3);
        Mockito.verify(captorService, Mockito.times(2)).execute(Mockito.any(CaptorModel.class));
        Assert.assertEquals("[1, a]", rtn.toString());
    }

}
