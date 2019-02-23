package com.maslke.spring.aop.advice;


import org.springframework.aop.framework.ProxyFactory;
import org.testng.annotations.Test;

@Test
public class GreetingAdviceTest {
    @Test
    public void adviceTest() {
        Waiter nativeWaiter = new NativeWaiter();
        GreetingAdvice advice = new GreetingAdvice();
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(nativeWaiter);
        proxyFactory.setInterfaces(nativeWaiter.getClass().getInterfaces());
        proxyFactory.addAdvice(advice);
        Waiter waiter = (Waiter)proxyFactory.getProxy();
        waiter.greetTo("maslke.111");
    }

    @Test
    public void adviceTest2() {
        Waiter nativeWaiter = new NativeWaiter();
        GreetingAdvice advice = new GreetingAdvice();
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(nativeWaiter);
        proxyFactory.setInterfaces(nativeWaiter.getClass().getInterfaces());
        proxyFactory.addAdvice(advice);
        Waiter waiter = (Waiter)proxyFactory.getProxy();
        waiter.serveTo("maslke.111");
    }
}