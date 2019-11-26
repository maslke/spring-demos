package com.maslke.spring.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class RequestCtrlInvocationHandler implements InvocationHandler {

    private Object target;

    public RequestCtrlInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().equals("request")) {
            // filter
        }

        return method.invoke(target, args);
    }

    public static void main(String[] args) {
        ISubject subject = (ISubject) Proxy.newProxyInstance(
                ClassLoader.getSystemClassLoader(), new Class[] {ISubject.class},
                new RequestCtrlInvocationHandler(new SubjectImpl()));
        subject.request();
    }
}

interface ISubject {
    void request();
}

class SubjectImpl implements ISubject {
    @Override
    public void request() {
        System.out.println("ISubject");
    }
}
