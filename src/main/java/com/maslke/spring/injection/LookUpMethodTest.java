package com.maslke.spring.injection;

import com.maslke.spring.ioc.Car;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author:maslke
 * @date:3/6/2019
 * @version:0.0.1
 */
public class LookUpMethodTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring" +
                "/beans.xml");
        MagicBoss boss1 = applicationContext.getBean("magicBoss", MagicBoss.class);

        Car car1 = boss1.getCar();
        Car car2 = boss1.getCar();

        System.out.println("car1 == car2: " + (car1 == car2));


    }
}
