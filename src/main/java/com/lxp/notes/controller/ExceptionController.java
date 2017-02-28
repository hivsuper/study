package com.lxp.notes.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lxp.notes.service.ExceptionService;

import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping(value = "/exception")
public class ExceptionController {
    private static final Logger LOG = LoggerFactory.getLogger(ExceptionController.class);
    @Resource
    private ExceptionService exceptionService;

    @ResponseBody
    @RequestMapping(value = "test", method = GET)
    @ApiOperation(value = "测试方法")
    public void test() {
        LOG.info("This is test API");
        exceptionService.test();
    }
}
