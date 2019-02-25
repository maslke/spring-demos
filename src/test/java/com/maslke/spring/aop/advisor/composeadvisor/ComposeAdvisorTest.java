package com.maslke.spring.aop.advisor.composeadvisor;

import com.maslke.spring.aop.advice.GreetingBeforeAdvice;
import com.maslke.spring.aop.advisor.Waiter2;
import com.maslke.spring.aop.advisor.WaiterDelegate;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.aop.support.ControlFlowPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.testng.annotations.Test;

/**
 * @author:maslke
 * @date:2/25/2019
 * @version:0.0.1
 */
@Test
public class ComposeAdvisorTest {
    @Test
    public void advisorTest() {

        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
        GreetingBeforeAdvice advice = new GreetingBeforeAdvice();
        Pointcut pointcut = new ControlFlowPointcut(WaiterDelegate.class, "service");
        Pointcut pointcut1 = new NameMatchMethodPointcut();
        ((NameMatchMethodPointcut) pointcut1).addMethodName("greetTo");

        ComposablePointcut composablePointcut = new ComposablePointcut();
        composablePointcut.intersection(pointcut).intersection(pointcut1);

        advisor.setAdvice(advice);
        advisor.setPointcut(composablePointcut);

        Waiter2 waiterTarget = new Waiter2();
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setProxyTargetClass(true);
        proxyFactory.setTarget(waiterTarget);
        proxyFactory.addAdvisor(advisor);

        Waiter2 waiter = (Waiter2) proxyFactory.getProxy();
        waiter.greetTo("maslke");
        waiter.serveTo("maslke");

        WaiterDelegate waiterDelegateTarget = new WaiterDelegate();
        waiterDelegateTarget.setWaiter2(waiter);

        waiterDelegateTarget.service("nana");

    }
}

