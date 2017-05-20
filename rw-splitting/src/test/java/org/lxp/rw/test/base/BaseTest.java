package org.lxp.rw.test.base;

import javax.annotation.Resource;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.context.WebApplicationContext;

/**
 * @Description: 单元测试基类
 * @author Super.Li
 * @date May 12, 2017
 */
@ContextConfiguration(locations = { "classpath*:spring/*.xml" })
public abstract class BaseTest {
    @Resource
    protected WebApplicationContext context;
}
