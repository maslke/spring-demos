package com.maslke.spring.aop.aspect;

import com.maslke.spring.aop.advice.NativeWaiter;
import com.maslke.spring.aop.advice.NaughtyWaiter;
import com.maslke.spring.aop.advice.Waiter;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.testng.annotations.Test;

@Test
public class AnnotationAspectTest {

    @Test
    public void aspectProxyTest() {
        Waiter waiter = new NativeWaiter();
        AspectJProxyFactory factory = new AspectJProxyFactory();
        factory.setTarget(waiter);
        factory.addAspect(AnnotationAspect.class);

        Waiter waiterProxy = factory.getProxy();
        waiterProxy.greetTo("maslke");

        NaughtyWaiter naughtyWaiter = new NaughtyWaiter();
        factory.setTarget(naughtyWaiter);
        factory.addAspect(AnnotationAspect.class);
        NaughtyWaiter naughtyWaiterTarget = factory.getProxy();
        naughtyWaiterTarget.greetTo("naughty-maslke");

    }
}
