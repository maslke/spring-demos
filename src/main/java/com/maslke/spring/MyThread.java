package com.maslke.spring;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class MyThread implements Runnable {


    private AtomicInteger atomicCount = new AtomicInteger(0);
    private int count = 0;

    private int getCount() {
        return this.atomicCount.get();
    }

    private static CountDownLatch countDownLatch = new CountDownLatch(3);

    private MyThread() {
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 100000; i++) {
                atomicCount.incrementAndGet();
                count++;
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        countDownLatch.countDown();
    }

    public static void main(String[] args) throws InterruptedException {
        AtomicInteger atomcIndex = new AtomicInteger(0);
        MyThread myThread = new MyThread();
        ExecutorService executorService = new ThreadPoolExecutor(3, 3, 0,
                TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(3), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                t.setName("Thread-" + atomcIndex.getAndIncrement());
                return t;
            }
        });
        executorService.execute(myThread);
        executorService.execute(myThread);
        executorService.execute(myThread);
        countDownLatch.await();
        System.out.print(myThread.getCount());
        System.out.println();
        System.out.println(myThread.count);
        executorService.shutdown();
    }
}
