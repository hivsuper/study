package com.lxp.activemq.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WelcomeController {
    private static final Logger LOG = LoggerFactory.getLogger(WelcomeController.class);

    @ResponseBody
    @RequestMapping(value = "/", method = GET)
    public String execute() {
        LOG.debug("This is welcome page");
        return "hello world";
    }
}
