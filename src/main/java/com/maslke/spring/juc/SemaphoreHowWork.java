package com.maslke.spring.juc;

import java.util.concurrent.Semaphore;

public class SemaphoreHowWork implements Runnable {

    private Semaphore semaphore;
    private int i;

    public SemaphoreHowWork(Semaphore semaphore, int i) {
        this.semaphore = semaphore;
        this.i = i;
    }

    @Override
    public void run() {
        try {
            boolean canAcquire = semaphore.tryAcquire();
            if (!canAcquire) {
                System.out.println("will be waiting...");
            }
            semaphore.acquire();
            System.out.println("acquire semaphore");
            work(i);

        }
        catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    private void work(int i) throws InterruptedException {
        System.out.println("do homework:" + i);
        Thread.sleep(10 * i);
        System.out.println("release semaphore");
        semaphore.release();
    }
}
