package com.maslke.spring.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class UserServiceHandler implements InvocationHandler {
    private Object target;

    public UserServiceHandler(Object target) {
        this.target = target;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("proxy...");
        Object obj = method.invoke(target, args);
        return obj;
    }
}
