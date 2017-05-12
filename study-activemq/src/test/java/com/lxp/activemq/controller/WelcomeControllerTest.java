package com.lxp.activemq.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Assert;
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
public class WelcomeControllerTest extends BaseTest {
    protected MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testExecute() throws Exception {
        String responseString = mockMvc.perform(get("/")).andExpect(status().isOk()).andDo(print()).andReturn()
                .getResponse().getContentAsString();
        Assert.assertNotNull(responseString);
    }
}
