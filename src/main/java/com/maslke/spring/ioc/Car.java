package com.maslke.spring.ioc;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class Car implements BeanNameAware, BeanFactoryAware, InitializingBean, DisposableBean {

    private String brand;
    private String color;
    private int maxSpeed;

    private BeanFactory beanFactory;

    private String beanName;

    public Car() {
        System.out.println("constructor: Car()");
    }

    public Car(String brand, String color, int maxSpeed) {
        this.brand = brand;
        this.color = color;
        this.maxSpeed = maxSpeed;
        System.out.println("constructor: Car(String, String, int)");
    }

    public void introduce() {
        System.out.println("brand: " + brand + ";color: " + color + ";maxSpeed: " + maxSpeed);
    }

    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("setBeanFactory...");
        this.beanFactory = beanFactory;
    }

    public void setBeanName(String s) {
        System.out.println("setBeanName...");
        this.beanName = s;
    }

    public void myInit() {
        System.out.println("init-method: myinit");
        this.maxSpeed = 250;
    }

    public void myDestory() {
        System.out.println("destroy-method: myDestroy()");
    }

    public void destroy() throws Exception {
        System.out.println("destroy: " + beanName);
    }

    public void afterPropertiesSet() throws Exception {
        System.out.println("init: " + beanName);
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }
}
