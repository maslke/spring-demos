package com.maslke.spring.aop.advice;

import org.springframework.aop.framework.ProxyFactory;
import org.testng.annotations.Test;

@Test
public class GreetingBeforeAdviceTest {

   @Test
    public void methodBeforeAdviceTest() {
       NativeWaiter target = new NativeWaiter();

       GreetingBeforeAdvice advice = new GreetingBeforeAdvice();
       GreetingAfterAdvice afterAdvice = new GreetingAfterAdvice();
       ProxyFactory pf = new ProxyFactory();
       pf.setTarget(target);
       pf.addAdvice(advice);
       pf.addAdvice(afterAdvice);

       Waiter proxy = (Waiter)pf.getProxy();
       proxy.greetTo("maslke");
   }

}