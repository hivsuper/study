package com.lxp.activemq.producer.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.annotation.Resource;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lxp.activemq.producer.service.MessageService;

@Controller
@RequestMapping(value = "/producer")
public class ProducerController {
    @Resource
    private ActiveMQQueue queueDestination;
    @Resource
    private MessageService messageService;

    @ResponseBody
    @RequestMapping(value = "/sendAString", method = POST)
    public void sendAString(@RequestParam String content) {
        messageService.send(queueDestination, content);
    }
}
