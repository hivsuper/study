package com.lxp.activemq.producer.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.lxp.activemq.test.base.BaseTest;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class ProducerControllerTest extends BaseTest {
    protected MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testSendAString() throws Exception {
        final String content = "#$%^*&()*&^%EDFGHKLGCJK";
        mockMvc.perform(post("/producer/sendAString").param("content", content)).andExpect(status().isOk())
                .andDo(print()).andReturn().getResponse().getContentAsString();
    }
}
