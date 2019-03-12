package com.maslke.spring.events;

import org.springframework.context.ApplicationListener;

public class MailSendEventListener implements ApplicationListener<MailSendEvent> {
    @Override
    public void onApplicationEvent(MailSendEvent mailSendEvent) {
        System.out.println("Send To: " + mailSendEvent.getTo());
    }
}
