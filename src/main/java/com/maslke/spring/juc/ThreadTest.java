package com.maslke.spring.juc;

/**
 * @author:maslke
 * @date:2019/8/20
 * @version:0.0.1
 */
public class ThreadTest {
    public void methodA() {
        for (int i = 0; i < 1000000; i++) {
            System.out.println("methodA:" + i);
        }
    }

    public synchronized void methodB() {
        for (int i = 0; i < 1000000; i++) {
            System.out.println("methodB:" + i);
        }
    }

    public static void main(String[] args) {
        final ThreadTest test = new ThreadTest();
        Thread threadA = new Thread(() -> test.methodA());
        Thread threaedB = new Thread(() -> test.methodB());
        threadA.start();
        threaedB.start();
    }
}
