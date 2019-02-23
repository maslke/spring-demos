package com.maslke.spring.aop.advice;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;

public class ConfigurableGreetingAdvice extends DelegatingIntroductionInterceptor implements Configurable {
    private ThreadLocal<Boolean> statusMap = new ThreadLocal<>();

    @Override
    public Object invoke(MethodInvocation mi) throws Throwable {
        if (statusMap.get() != null && statusMap.get()) {
            Object[] args = mi.getArguments();
            String clientName = (String) args[0];
            System.out.println("The client name : " + clientName);
            Object obj = mi.proceed();
            System.out.println("please enjoy yourself");
            return obj;
        }
        else {
            return mi.proceed();
        }
    }

    @Override
    public void setAcitve(boolean active) {
        statusMap.set(active);
    }
}
