package com.maslke.spring;

import java.util.Map;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Exchanger;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

/**
 * @author:maslke
 * @date:4/9/2019
 * @version:0.0.1
 */
@Test
public class ConcurrentTest {

    @Test
    public void countDownLatchTest() throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(20);
        new Thread(){
            @Override
            public void run() {
                System.out.println("1");
                countDownLatch.countDown();
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                System.out.println("2");
                countDownLatch.countDown();
            }
        }.run();
        countDownLatch.await(10000, TimeUnit.MILLISECONDS);
        System.out.println(3);
    }

    @Test
    public void joinTest() throws InterruptedException {
        Thread t1 = new Thread() {
            @Override
            public void run() {
                System.out.println("1");
            }
        };
        Thread t2 = new Thread() {
            @Override
            public void run() {
                System.out.println("2");
            }
        };
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("3");
    }

    @Test
    public void cyclicBarrierTest() throws InterruptedException, BrokenBarrierException {
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(3, new Thread(){
            @Override
            public void run() {
                System.out.println("first");
            }
        });
        new Thread(){
            @Override
            public void run() {
                try {
                    cyclicBarrier.await();
                } catch (Exception ex) {

                }
                System.out.println("1");
            }
        }.start();

        new Thread() {
            @Override
            public void run(){
                try {
                    cyclicBarrier.await();
                } catch (Exception ex) {

                }
                System.out.println("2");
            }
        }.start();
        cyclicBarrier.await();
        System.out.println("3");
    }

    @Test
    public void exchangerTest() throws InterruptedException {
        final Exchanger<String> exchanger = new Exchanger<>();
        new Thread() {
            @Override
            public void run() {
                String a = "test";
                try {
                    exchanger.exchange(a);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                String b = "test";
                try {
                    String a = exchanger.exchange(b);
                    System.out.println(a);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }.start();
    }

    public void cyclicBarrierTest2() {
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(5);
        Executor executor = Executors.newFixedThreadPool(4);
        final ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<String, Integer>(16);
        for (int i = 0; i < 4; i++) {
            executor.execute(new Thread(){
                @Override
                public void run() {
                    map.put(Thread.currentThread().getName(), 1);
                    try {
                        cyclicBarrier.await();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });
        }
        try {

            cyclicBarrier.await();
        } catch (Exception ex) {

        }
        int sum = 0;
        for (Map.Entry<String, Integer> sheet: map.entrySet()) {
            sum += sheet.getValue();
        }
        assertEquals(sum, 4);
    }
}
