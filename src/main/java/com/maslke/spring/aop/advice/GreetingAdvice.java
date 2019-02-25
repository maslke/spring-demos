package com.maslke.spring.aop.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class GreetingAdvice implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        Object[] args = methodInvocation.getArguments();
        String clientName = (String) args[0];
        System.out.println("The client name :" + clientName);
        Object obj = methodInvocation.proceed();
        System.out.println("please enjoy yourself");
        return obj;
    }
}
