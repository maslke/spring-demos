package com.maslke.spring.aop;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class RequestCtrlCallback implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        if (method.getName().equals("request")) {

        }
        return methodProxy.invokeSuper(o, objects);
    }

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Requestable.class);
        enhancer.setCallback(new RequestCtrlCallback());
        Requestable proxy = (Requestable) enhancer.create();
        proxy.request();
    }
}

class Requestable {
    public void request() {
        System.out.println("cglib proxy");
    }
}


