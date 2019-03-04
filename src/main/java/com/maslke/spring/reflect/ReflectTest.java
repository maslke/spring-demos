package com.maslke.spring.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * @author:maslke
 * @date:3/4/2019
 * @version:0.0.1
 */
public class ReflectTest {

    public static void displayClassInfos(String classFullName) throws Exception {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Class clazz = classLoader.loadClass(classFullName);
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            System.out.println("method name: " + method.getName());
            Class[] classes = method.getParameterTypes();
            System.out.println("params type:");
            for (Class c : classes) {
                System.out.println(c.getCanonicalName());
            }
            System.out.println("return type:" + method.getReturnType().getCanonicalName());

        }

    }

    public static Car initByDefaultContructor() throws Exception {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Class clazz = classLoader.loadClass("com.maslke.spring.reflect.Car");

        Constructor constructor = clazz.getDeclaredConstructor((Class[]) null);
        Car car = (Car) constructor.newInstance();

        Method[] methods = clazz.getMethods();
        for (Method m : methods) {
            System.out.println(m.getName());
        }

        Method method = clazz.getMethod("setBrand", String.class);
        method.invoke(car, "Audi");

        Method setColor = clazz.getMethod("setColor", String.class);
        setColor.invoke(car, "Red");

        Method setMaxSpeed = clazz.getMethod("setMaxSpeed", int.class);
        setMaxSpeed.invoke(car, 300);

        return car;
    }

    public static Car initByParamsConstructor() throws Exception {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Class clazz = classLoader.loadClass("com.maslke.spring.reflect.Car");
        Constructor constructor = clazz.getDeclaredConstructor(String.class, String.class, int.class);

        return (Car) constructor.newInstance("Das Auto", "Black", 200);
    }


    public static void main(String[] args) throws Exception {
        Car car = initByDefaultContructor();
        car.introduce();

        Car car2 = initByParamsConstructor();
        car2.introduce();

        displayClassInfos("com.maslke.spring.reflect.Car");
    }
}
