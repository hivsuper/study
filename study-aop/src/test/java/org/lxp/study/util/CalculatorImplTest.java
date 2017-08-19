package org.lxp.study.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.lxp.study.aop.AbstractHandler;
import org.lxp.study.aop.ProxyFactory;

public class CalculatorImplTest {

    @Test
    public void testAdd() throws Exception {
        Calculator calculator = new CalculatorImpl();
        AbstractHandler before = new AbstractHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("...before...");
                return method.invoke(getTargetObject(), args);
            }
        };
        AbstractHandler after = new AbstractHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Object result = method.invoke(getTargetObject(), args);
                System.out.println("...after...");
                return result;
            }
        };
        AbstractHandler around = new AbstractHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                StringBuilder sb = new StringBuilder();
                for (Object arg : args) {
                    sb.append(arg).append(",");
                }
                System.out.println("Parameters:" + sb.substring(0, sb.length() - 1));
                Object result = method.invoke(getTargetObject(), args);
                System.out.println("Result:" + result);
                return result;
            }
        };
        List<AbstractHandler> handlers = new ArrayList<AbstractHandler>();
        handlers.add(before);
        handlers.add(after);
        handlers.add(around);
        Calculator proxy = (Calculator) ProxyFactory.getProxy(calculator, handlers);
        Assert.assertEquals(30, proxy.add(20, 10));
    }

}
