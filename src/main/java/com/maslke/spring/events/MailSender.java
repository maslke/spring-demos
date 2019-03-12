package com.maslke.spring.events;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class MailSender implements ApplicationContextAware {
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public void sendMail(String to) {
        System.out.println("MailSender:");
        MailSendEvent event = new MailSendEvent(applicationContext, "maslke");
        applicationContext.publishEvent(event);
    }


}
