package com.maslke.spring;

import java.util.concurrent.locks.ReentrantLock;

public class MyThread implements Runnable {
    private int count = 0;
    private ReentrantLock lock = new ReentrantLock();

    public int getCount() {
        return this.count;
    }

    @Override
    public void run() {
        lock.lock();
        try {
            for (int i = 1; i <= 100000; i++) {
                count++;
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyThread myThread = new MyThread();
        Thread t1 = new Thread(myThread);
        Thread t2 = new Thread(myThread);
        Thread t3 = new Thread(myThread);
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
        System.out.print(myThread.getCount());
    }
}
