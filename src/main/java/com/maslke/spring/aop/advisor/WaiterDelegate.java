package com.maslke.spring.aop.advisor;

/**
 * @author:maslke
 * @date:2/25/2019
 * @version:0.0.1
 */
public class WaiterDelegate {
    private Waiter2 waiter2;

    public void service(String clientName) {
        waiter2.greetTo(clientName);
        waiter2.serveTo(clientName);
    }

    public void setWaiter2(Waiter2 w) {
        this.waiter2 = w;
    }
}
