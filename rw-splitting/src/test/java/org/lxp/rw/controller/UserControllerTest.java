package org.lxp.rw.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.lxp.rw.model.User;
import org.lxp.rw.service.UserService;
import org.lxp.rw.test.base.BaseTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@Transactional
public class UserControllerTest extends BaseTest {
    private static final String testQuery = "测试查询";
    @Resource
    private UserService userService;
    protected MockMvc mockMvc;
    private int deleteUserId;
    private int getUserId;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

        deleteUserId = userService.add("测试删除").getId();
        getUserId = userService.add(testQuery).getId();
    }

    @Test
    public void testAdd() throws Exception {
        final String name = "测试添加";
        String s = mockMvc.perform(post("/user/add.json").param("name", name)).andExpect(status().isOk()).andDo(print())
                .andReturn().getResponse().getContentAsString();
        User tmp = objectMapper.readValue(s, User.class);
        Assert.assertEquals(name, tmp.getName());
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete("/user/" + deleteUserId + ".json")).andExpect(status().isOk()).andDo(print()).andReturn()
                .getResponse().getContentAsString();
    }

    @Test
    public void testQuery() throws Exception {
        String s = mockMvc.perform(get("/user/" + getUserId + ".json")).andExpect(status().isOk()).andDo(print())
                .andReturn().getResponse().getContentAsString();
        User tmp = objectMapper.readValue(s, User.class);
        Assert.assertEquals(testQuery, tmp.getName());
    }
}
