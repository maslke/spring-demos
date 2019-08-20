package com.maslke.spring.juc;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author:maslke
 * @date:2019/8/20
 * @version:0.0.1
 */
public class ThreadTest2 {

    private ReentrantLock lock = new ReentrantLock();

    public void methodA() {
        lock.lock();
        try {
            for (int i = 0; i < 10000; i++) {
                System.out.println("methodA:" + i);
            }
        } finally {
            lock.unlock();
        }

    }

    public void methodB() {
        lock.lock();
        try {
            for (int i = 0; i < 10000; i++) {
                System.out.println("methodB:" + i);
            }
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws Exception {
        final ThreadTest2 test = new ThreadTest2();
        Thread threadA = new Thread(() -> test.methodA());
        Thread threaedB = new Thread(() -> test.methodB());
        threaedB.start();
        Thread.sleep(1000);
        threadA.start();
    }
}
