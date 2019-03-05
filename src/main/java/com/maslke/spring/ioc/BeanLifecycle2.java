package com.maslke.spring.ioc;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanL ifecycle2 {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/beans.xml");
        Car car = applicationContext.getBean("car", Car.class);
        car.introduce();
        applicationContext.close();
    }
}
