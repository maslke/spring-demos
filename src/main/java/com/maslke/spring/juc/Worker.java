package com.maslke.spring.juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Worker {
    public static void main(String[] args) throws InterruptedException {
        Executor executor = Executors.newFixedThreadPool(100);
        CountDownLatch latch = new CountDownLatch(100);
        for (int i = 0; i < 100; i++) {
            executor.execute(new HomeWork(latch, i + 1));
        }
        latch.await();
        System.out.println("all done");
    }
}
