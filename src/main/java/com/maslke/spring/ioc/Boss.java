package com.maslke.spring.ioc;

/**
 * @author:maslke
 * @date:3/7/2019
 * @version:0.0.1
 */
public class Boss {

    private String name;
    private Car car = new Car();

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
