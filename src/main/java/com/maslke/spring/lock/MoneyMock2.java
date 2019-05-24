package com.maslke.spring.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MoneyMock2 {
    public static void main(String[] args) throws InterruptedException {
        final Account2 src = new Account2(10000);
        final Account2 target = new Account2(10000);
        final CountDownLatch countDownLatch = new CountDownLatch(9999);

        ExecutorService executorService = Executors.newFixedThreadPool(100);

        for (int i = 0; i < 9999; i++) {

            executorService.execute(() -> {
                src.transfer(target, 1);
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        System.out.println("src=" + src.getBalance());
        System.out.println("target=" + target.getBalance());
    }
}

class Account2 {
    private Integer balance;
    private final Allocator2 allocator;

    public Account2(Integer balance) {
        this.balance = balance;
        allocator = Allocator2.getInstance();
    }


    public void transfer(Account2 target, Integer money) {
        while (!allocator.accquire(this, target)) {

        }
        this.balance -= money;
        target.setBalance(target.getBalance() + money);
        allocator.release(this, target);
    }

    public Integer getBalance() {
        return this.balance;
    }

    public void setBalance(Integer b) {
        this.balance = b;
    }
}

class Allocator2 {

    static class AllocatorSingle {
        public static Allocator2 instance = new Allocator2();
    }

    public static Allocator2 getInstance() {
        return AllocatorSingle.instance;
    }

    private List<Account2> locks = new ArrayList<>();

    public synchronized boolean accquire(Account2 a, Account2 b) {
        if (locks.contains(a) || locks.contains(b)) {
            return false;
        }
        locks.add(a);
        locks.add(b);
        return true;

    }

    public synchronized void release(Account2 a, Account2 b) {
        locks.remove(a);
        locks.remove(b);
    }
}

