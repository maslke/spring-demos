package com.maslke.spring.aop.aspect;

import com.maslke.spring.aop.advice.NativeWaiter;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author:maslke
 * @date:2/27/2019
 * @version:0.0.1
 */
@Test
public class ExecutionAspectTest {

    @Test
    public void aspectTest() {
        NativeWaiter nativeWaiter = new NativeWaiter();
        AspectJProxyFactory proxyFactory = new AspectJProxyFactory();
        proxyFactory.setTarget(nativeWaiter);
        proxyFactory.addAspect(ExecutionAspect.class);

        NativeWaiter waiter = (NativeWaiter) proxyFactory.getProxy();
        waiter.greetTo("maslke");
        waiter.greetTo(10086);
    }

}