package com.maslke.spring.juc;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class TimoutWaitExample {
    private static volatile boolean ready = false;
    private static final Random random = new Random();

    private static final ReentrantLock lock2 = new ReentrantLock();
    private static final Condition condition = lock2.newCondition();

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            while (true) {
                lock2.lock();
                try {
                    ready = random.nextInt(100) < 20;
                    if (ready) {
                        condition.signal();
                    }
                }
                finally {

                    lock2.unlock();
                }

                try {
                    Thread.sleep(random.nextInt(500));

                }
                catch (InterruptedException ex) {
                    System.out.println(ex.getMessage());
                }
            }

        });
        t.setDaemon(true);
        t.start();
        waiter(1000);
    }

    public static void waiter(final long timeout) throws InterruptedException {
        if (timeout < 0) {
            throw new IllegalArgumentException();
        }
        lock2.lock();
        long start = System.currentTimeMillis();
        long nanoTime = TimeUnit.MILLISECONDS.toNanos(timeout);
        try {
            while (!ready) {
                if (nanoTime <= 0) {
                    break;
                }
//                long now = System.currentTimeMillis();
//                long waitTime = timeout - (now - start);
//                if (waitTime <= 0) {
//                    break;
//                }
                nanoTime = condition.awaitNanos(nanoTime);
            }

            if (ready) {
                guardAction();
            }
            else {
                System.out.println("wait time out, unable to execution target action!");
            }
        }
        finally {
            lock2.unlock();
        }
    }

    private static void guardAction() {
        System.out.println("Take some action.");
    }
}
