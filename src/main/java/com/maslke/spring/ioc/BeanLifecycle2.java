package com.maslke.spring.ioc;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanLifecycle2 {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/beans.xml");
        Car car = applicationContext.getBean("car", Car.class);
        car.introduce();
        applicationContext.close();
    }
}
