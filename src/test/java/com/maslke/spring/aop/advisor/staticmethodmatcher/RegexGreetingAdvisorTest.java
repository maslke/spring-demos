package com.maslke.spring.aop.advisor.staticmethodmatcher;

import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.RegexpMethodPointcutAdvisor;
import org.testng.annotations.Test;

import com.maslke.spring.aop.advice.GreetingAdvice;
import com.maslke.spring.aop.advisor.Seller;
import com.maslke.spring.aop.advisor.Waiter2;

@Test
public class RegexGreetingAdvisorTest {
    @Test
    public void greetingAdvisorTest() {
        RegexpMethodPointcutAdvisor advisor = new RegexpMethodPointcutAdvisor();
        advisor.setPattern(".*greet.*");
        Waiter2 waiterTarget = new Waiter2();
        Seller sellerTarget = new Seller();
        GreetingAdvice advice = new GreetingAdvice();
        advisor.setAdvice(advice);
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.addAdvisor(advisor);
        proxyFactory.setTarget(waiterTarget);
        proxyFactory.setProxyTargetClass(true);
        Waiter2 waiter = (Waiter2) proxyFactory.getProxy();
        waiter.greetTo("maslke");

        waiter.greetAgain("maslke");

        proxyFactory.setTarget(sellerTarget);
        Seller seller = (Seller) proxyFactory.getProxy();
        seller.greetTo("nan");
    }
}
