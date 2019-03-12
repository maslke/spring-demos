package com.maslke.spring.events;

import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;

public class MailSendEvent extends ApplicationContextEvent {

    private String to;

    public MailSendEvent(ApplicationContext applicationContext, String to) {
        super(applicationContext);
        this.to = to;
    }

    public String getTo() {
        return this.to;
    }

}
