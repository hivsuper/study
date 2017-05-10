package com.lxp.notes.controller;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lxp.notes.test.base.BaseTest;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class ExceptionControllerTest extends BaseTest {
    protected MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(documentationConfiguration(restDocumentation))
                .build();
    }

    @Test
    public void test_二_1_no() throws Exception {
        RestDocumentationResultHandler handler = document(ExceptionController.捕获_RUNTIME_EXCEPTION,
                preprocessResponse(prettyPrint()));
        List<String> list = new ArrayList<>();
        list.add("1");
        String responseString = mockMvc
                .perform(post("/二/1+2-no").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).param("index", "1")
                        .content(new ObjectMapper().writeValueAsString(list)))
                .andExpect(status().isOk()).andDo(handler).andReturn().getResponse().getContentAsString();
        Assert.assertNotNull(responseString);
    }

}
