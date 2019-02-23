package com.maslke.spring.aop.advice;

public class NativeWaiter implements Waiter {
    @Override
    public void greetTo(String clientName) {
        System.out.println("greet to " + clientName + "..");
    }

    @Override
    public void serveTo(String clientName) {
        System.out.println("serve to " + clientName + "..");
    }
}
