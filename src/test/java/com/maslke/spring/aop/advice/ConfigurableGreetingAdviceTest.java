package com.maslke.spring.aop.advice;

import org.springframework.aop.framework.ProxyFactory;
import org.testng.annotations.Test;


@Test
public class ConfigurableGreetingAdviceTest {
    @Test
    public void adviceTest1() {
        Waiter nativeWaiter = new NativeWaiter();
        ConfigurableGreetingAdvice advice = new ConfigurableGreetingAdvice();
        advice.setAcitve(true);
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(nativeWaiter);
        proxyFactory.setInterfaces(nativeWaiter.getClass().getInterfaces());
        proxyFactory.addAdvice(advice);
        Waiter waiter = (Waiter) proxyFactory.getProxy();
        waiter.serveTo("maslke--");
    }

    @Test
    public void adviceTest2() {
        Waiter nativeWaiter = new NativeWaiter();
        ConfigurableGreetingAdvice advice = new ConfigurableGreetingAdvice();
        advice.setAcitve(false);
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(nativeWaiter);
        proxyFactory.setInterfaces(nativeWaiter.getClass().getInterfaces());
        proxyFactory.addAdvice(advice);
        Waiter waiter = (Waiter) proxyFactory.getProxy();
        waiter.serveTo("maslke--");
    }
}