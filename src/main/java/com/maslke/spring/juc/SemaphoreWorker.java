package com.maslke.spring.juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreWorker {
    public static void main(String[] args) throws InterruptedException {
        Executor   = Executors.newFixedThreadPool(100);
        Semaphore semaphore = new Semaphore(50);
        for (int i = 0; i < 100; i++) {
            executor.execute(new SemaphoreHowWork(semaphore, i + 1));
        }
        System.out.println("all done");
    }
}
