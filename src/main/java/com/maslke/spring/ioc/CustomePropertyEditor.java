package com.maslke.spring.ioc;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author:maslke
 * @date:3/7/2019
 * @version:0.0.1
 */
public class CustomePropertyEditor {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring" +
                "/beans2.xml");
        Boss boss = applicationContext.getBean("boss", Boss.class);
        Car car = boss.getCar();
        System.out.println("brand: " + car.getBrand());
        System.out.println("color: " + car.getColor());
        System.out.println("maxSpeed: " + car.getMaxSpeed());
    }
}
