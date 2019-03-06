package com.maslke.spring.injection;

import com.maslke.spring.ioc.Car;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author:maslke
 * @date:3/6/2019
 * @version:0.0.1
 */
public class MethodReplacerTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext =
                 new ClassPathXmlApplicationContext("classpath:spring/beans.xml");
        Boss1 boss = applicationContext.getBean("boss1", Boss1.class);
        Car car = boss.getCar();
        System.out.println(car.getBrand());

    }
}
