package com.maslke.spring.injection;

import com.maslke.spring.ioc.Car;
import java.lang.reflect.Method;
import org.springframework.beans.factory.support.MethodReplacer;

/**
 * @author:maslke
 * @date:3/6/2019
 * @version:0.0.1
 */
public class Boss2 implements MethodReplacer {
    public Object reimplement(Object o, Method method, Object[] objects) throws Throwable {
        Car car = new Car();
        car.setBrand("qq");

        return car;
    }
}
