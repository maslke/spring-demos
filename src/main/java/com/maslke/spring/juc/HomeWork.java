package com.maslke.spring.juc;

import java.util.concurrent.CountDownLatch;

public class HomeWork implements Runnable {

    private CountDownLatch latch;
    private int i;

    public HomeWork(CountDownLatch latch, int i) {
        this.latch = latch;
        this.i = i;
    }

    @Override
    public void run() {
        try {
            work(i);
            this.latch.countDown();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    private void work(int i) throws InterruptedException {
        System.out.println("do homework:" + i);
        Thread.sleep(10 * i);
    }
}
