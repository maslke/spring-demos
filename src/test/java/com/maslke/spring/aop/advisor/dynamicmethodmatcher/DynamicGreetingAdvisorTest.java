package com.maslke.spring.aop.advisor.dynamicmethodmatcher;

import com.maslke.spring.aop.advice.GreetingBeforeAdvice;
import com.maslke.spring.aop.advisor.Seller;
import com.maslke.spring.aop.advisor.Waiter2;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.testng.annotations.Test;

/**
 * @author:maslke
 * @date:2/25/2019
 * @version:0.0.1
 */
@Test
public class DynamicGreetingAdvisorTest {
    @Test
    public void advisorTest() {
        Waiter2 waiter2Target = new Waiter2();
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
        GreetingBeforeAdvice advice = new GreetingBeforeAdvice();
        advisor.setPointcut(new DynamicGreetingAdvisor());
        advisor.setAdvice(advice);
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(waiter2Target);
        proxyFactory.setProxyTargetClass(true);
        proxyFactory.addAdvisor(advisor);

        Waiter2 waiter = (Waiter2) proxyFactory.getProxy();
        waiter.greetTo("maslke");

        waiter.greetTo("maslke222");
    }
}