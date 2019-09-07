package com.maslke.spring.juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 演示了在单线程线程池中造成死锁的一种典型的情况
 * 线程饥饿死锁
 *
 * 发生死锁的四个条件如下：
 * 1）资源x和y只能被单个线程拥有
 * 2）不可破坏抢占条件
 * 3）循环等待。
 * 4）占用x的时候，去获取y，不释放对x的占有
 */
public class SingleThreadExecutorDeadLockDemo {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<Integer> f = executorService.submit(() -> {
            Future<Integer> future = executorService.submit(() -> {
                return 3;
            });
            try {
                return future.get();
            }
            catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            return -1;
        });
        try {
            System.out.println(f.get().toString());

        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        executorService.shutdown();
    }
}
