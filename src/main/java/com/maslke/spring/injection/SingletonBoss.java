package com.maslke.spring.injection;

import com.maslke.spring.ioc.Car;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SingletonBoss implements BeanFactoryAware {

    private BeanFactory beanFactory;

    private Car car;



    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Car getCar() {
        //return this.beanFactory.getBean(com.maslke.spring.ioc.Car.class);
        return this.car;
    }

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/beans3.xml");
        SingletonBoss boss = applicationContext.getBean("boss", SingletonBoss.class);
        Car car = boss.getCar();
        Car car2 = boss.getCar();
        System.out.print(car == car2);

    }


}
