package com.maslke.spring.aop.advisor.introductionadvisor;

import com.maslke.spring.aop.advice.ConfigurableGreetingAdvice;
import com.maslke.spring.aop.advice.NativeWaiter;
import com.maslke.spring.aop.advice.Waiter;
import org.aopalliance.aop.Advice;
import org.springframework.aop.IntroductionAdvisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultIntroductionAdvisor;
import org.testng.annotations.Test;

/**
 * @author:maslke
 * @date:2/25/2019
 * @version:0.0.1
 */
@Test
public class IntroductionAdvisorTest {
    @Test
    public void advisorTest() {
        Advice advice = new ConfigurableGreetingAdvice();
        ((ConfigurableGreetingAdvice) advice).setAcitve(true);
        IntroductionAdvisor advisor = new DefaultIntroductionAdvisor(advice);
        Waiter waiterTarget = new NativeWaiter();
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setProxyTargetClass(true);
        proxyFactory.addAdvisor(advisor);
        proxyFactory.setTarget(waiterTarget);

        Waiter waiter = (Waiter) proxyFactory.getProxy();
        waiter.greetTo("maslke");
    }
}
