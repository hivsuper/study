package org.lxp.mock.captor.impl;

import java.util.Collections;

import org.lxp.mock.captor.CaptorModel;
import org.lxp.mock.captor.CaptorService;
import org.lxp.mock.captor.RealCaptorService;

public class RealCaptorServiceImpl implements RealCaptorService {
    private CaptorService captorService;

    public RealCaptorServiceImpl(CaptorService captorService) {
        this.captorService = captorService;
    }

    @Override
    public void batchExecute(String propertyString, int propertyInt) {
        captorService.execute(Collections.singletonList(new CaptorModel(propertyString, propertyInt)));
    }

    @Override
    public void execute(String propertyString, int propertyInt) {
        captorService.execute(new CaptorModel(propertyString, propertyInt));
    }

    @Override
    public void execute(CaptorModel captorModel) {
        captorService.execute(captorModel);
    }
}