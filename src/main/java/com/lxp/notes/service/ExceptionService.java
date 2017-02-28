package com.lxp.notes.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ExceptionService {
    private static final Logger LOG = LoggerFactory.getLogger(ExceptionService.class);

    public void test() {
        LOG.info("This is test method.");
    }
}
