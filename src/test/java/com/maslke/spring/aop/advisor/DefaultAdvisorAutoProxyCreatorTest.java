package com.maslke.spring.aop.advisor;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;

@Test
public class DefaultAdvisorAutoProxyCreatorTest {
    @Test
    public void autoProxyTest() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/context2.xml");
        Waiter2 waiter2 = (Waiter2) context.getBean("waiter");
        waiter2.greetTo("maslke");
        waiter2.serveTo("maslke");
        Seller seller = (Seller) context.getBean("seller");
        seller.greetTo("maslke");

    }
}
