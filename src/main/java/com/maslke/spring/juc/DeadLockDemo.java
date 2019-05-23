package com.maslke.spring.juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 演示死锁情况的发生
 */
public class DeadLockDemo {

    private static final Object lockA = new Object();
    private static final Object lockB = new Object();

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(2);
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    synchronized (lockA) {
                        System.out.println("thread 1 : acquire lock a");
                        Thread.sleep(20000);
                        synchronized (lockB) {
                            System.out.println("thread 1 : acquire lock b");
                        }
                        latch.countDown();
                    }

                }
                catch (InterruptedException ex) {

                }

            }
        });

        executor.execute(new Runnable() {
            @Override
            public void run() {
                synchronized (lockB) {
                    System.out.println("thread 2 : acquire lock b");
                    synchronized (lockA) {
                        System.out.println("thread 2 : acquire lock b");
                    }
                }
                latch.countDown();
            }
        });
        try {
            if (latch.await(10000, TimeUnit.MILLISECONDS)) {
                System.out.println("no dead lock");
            } else {
                System.out.println("dead lock");
            }
        }
        catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            System.out.println("dead lock");
        }
        executor.shutdown();

    }
}
