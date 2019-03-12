package com.maslke.spring.events;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class MailSenderTest {

    @Test
    public void testSendMail() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/beans.xml");
        MailSender mailSender = applicationContext.getBean("mailSender", MailSender.class);
        mailSender.sendMail("maslke");

    }
}