package com.maslke.spring.ioc;

import com.maslke.spring.aop.advice.Waiter;

import java.util.Iterator;
import java.util.ServiceLoader;

public class ServiceLoaderDemo {
    public static void main(String[] args) {
        ServiceLoader<Waiter> serviceLoader = ServiceLoader.load(Waiter.class);
        Iterator<Waiter> iterator = serviceLoader.iterator();
        while (iterator.hasNext()) {
            Waiter waiter = iterator.next();
            System.out.println(waiter);
        }
    }
}
