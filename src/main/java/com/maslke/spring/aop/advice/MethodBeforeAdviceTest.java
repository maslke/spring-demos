package com.maslke.spring.aop.advice;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MethodBeforeAdviceTest {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");
        Waiter waiter = (Waiter) applicationContext.getBean("waiter");
        waiter.greetTo("maslke@");
    }
}
