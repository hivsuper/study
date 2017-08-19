package org.lxp.study.aop;

import java.lang.reflect.Proxy;
import java.util.List;

public class ProxyFactory {
    public static Object getProxy(Object targetObject, List<AbstractHandler> handlers) {
        Object proxyObject = null;
        if (handlers.size() > 0) {
            proxyObject = targetObject;
            for (AbstractHandler abstractHandler : handlers) {
                abstractHandler.setTargetObject(proxyObject);
                proxyObject = Proxy.newProxyInstance(targetObject.getClass().getClassLoader(),
                        targetObject.getClass().getInterfaces(), abstractHandler);
            }
            return proxyObject;
        } else {
            return targetObject;
        }
    }
}
