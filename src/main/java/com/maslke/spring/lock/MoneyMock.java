package com.maslke.spring.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class MoneyMock {
    public static void main(String[] args) throws InterruptedException {
        final Account src = new Account(10000);
        final Account target = new Account(10000);
        final CountDownLatch countDownLatch = new CountDownLatch(9999);

        for (int i = 0; i < 9999; i++) {
            new Thread() {
                @Override
                public void run() {
                    src.transfer(target, 1);
                    countDownLatch.countDown();
                }
            }.start();
        }
        countDownLatch.await();
        System.out.println("src=" + src.getBalance());
        System.out.println("target=" + target.getBalance());
    }
}

class Account {
    private Integer balance;
    private final Allocator allocator;

    public Account(Integer balance) {
        this.balance = balance;
        allocator = Allocator.getInstance();
    }


    public void transfer(Account target, Integer money) {
        allocator.accquire(this, target);
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

class Allocator {

    static class AllocatorSingle {
        public static Allocator instance = new Allocator();
    }

    public static Allocator getInstance() {
        return AllocatorSingle.instance;
    }

    private List<Account> locks = new ArrayList<>();

    public synchronized void accquire(Account a, Account b) {
        while (locks.contains(a) || locks.contains(b)) {
            try {
                wait();
            }
            catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
        }
        locks.add(a);
        locks.add(b);

    }

    public synchronized void release(Account a, Account b) {
        locks.remove(a);
        locks.remove(b);
        notifyAll();
    }
}

