package com.maslke.spring.aop.advice;

/**
 * @author:maslke
 * @date:2/27/2019
 * @version:0.0.1
 */
public class NaughtyWaiter implements Waiter {
    @NeedTest
    public void greetTo(String clientName) {
        System.out.println("naughty greet to " + clientName + "...");
    }

    public void serveTo(String clientName) {
        System.out.println("serve to " + clientName + "...");
    }


}
