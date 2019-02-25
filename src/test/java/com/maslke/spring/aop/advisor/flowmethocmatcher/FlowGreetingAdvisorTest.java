package com.maslke.spring.aop.advisor.flowmethocmatcher;

import com.maslke.spring.aop.advice.GreetingBeforeAdvice;
import com.maslke.spring.aop.advisor.Waiter2;
import com.maslke.spring.aop.advisor.WaiterDelegate;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.ControlFlowPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.testng.annotations.Test;

/**
 * @author:maslke
 * @date:2/25/2019
 * @version:0.0.1
 */
@Test
public class FlowGreetingAdvisorTest {
    @Test
    public void advisorTest() {

        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
        GreetingBeforeAdvice advice = new GreetingBeforeAdvice();
        ControlFlowPointcut pointcut = new ControlFlowPointcut(WaiterDelegate.class, "service");
        advisor.setAdvice(advice);
        advisor.setPointcut(pointcut);

        Waiter2 waiterTarget = new Waiter2();
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setProxyTargetClass(true);
        proxyFactory.setTarget(waiterTarget);
        proxyFactory.addAdvisor(advisor);

        Waiter2 waiter = (Waiter2) proxyFactory.getProxy();
        waiter.serveTo("maslke");

        WaiterDelegate waiterDelegateTarget = new WaiterDelegate();
        waiterDelegateTarget.setWaiter2(waiterTarget);

        proxyFactory.setTarget(waiterDelegateTarget);

        WaiterDelegate waiterDelegate = (WaiterDelegate) proxyFactory.getProxy();
        waiterDelegate.service("nana");

    }
}
