package com.maslke.spring.aop.advisor;

public class Waiter2 {
    public void greetTo(String name) {
        System.out.println("waiter greet to " + name + "...");
    }

    public void serveTo(String name) {
        System.out.println("waiter serve to " + name + "...");
    }
}
