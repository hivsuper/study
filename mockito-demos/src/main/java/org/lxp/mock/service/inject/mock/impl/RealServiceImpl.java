package org.lxp.mock.service.inject.mock.impl;

import javax.annotation.Resource;
import javax.inject.Named;

import org.lxp.mock.service.inject.mock.InjectService;
import org.lxp.mock.service.inject.mock.RealService;

@Named("realService")
public class RealServiceImpl implements RealService {
    @Resource
    private InjectService injectService;

    @Override
    public String getName() {
        return String.format("Real Name is %s", injectService.getName());
    }

}
