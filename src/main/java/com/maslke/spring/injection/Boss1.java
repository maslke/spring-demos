package com.maslke.spring.injection;

import com.maslke.spring.ioc.Car;

/**
 * @author:maslke
 * @date:3/6/2019
 * @version:0.0.1
 */
public class Boss1 {
    public Car getCar() {
        Car car = new Car();
        car.setBrand("BMW");

        return car;
    }

}
