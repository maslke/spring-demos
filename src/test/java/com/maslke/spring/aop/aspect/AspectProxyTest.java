package com.maslke.spring.aop.aspect;

import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.testng.annotations.Test;

import com.maslke.spring.aop.advice.NativeWaiter;
import com.maslke.spring.aop.advice.Waiter;

@Test
public class AspectProxyTest {

    @Test
    public void aspectProxyTest() {
        Waiter waiter = new NativeWaiter();
        AspectJProxyFactory factory = new AspectJProxyFactory();
        factory.setTarget(waiter);
        factory.addAspect(BeforeGreetingAspect.class);

        Waiter waiterProxy = (Waiter) factory.getProxy();
        waiterProxy.greetTo("maslke");

        waiterProxy.serveTo("maslke");
    }
}
