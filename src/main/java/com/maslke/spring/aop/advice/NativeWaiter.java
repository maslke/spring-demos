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

    public void greetTo(Integer value) {
        System.out.println("the value is :" + value);
    }

}
