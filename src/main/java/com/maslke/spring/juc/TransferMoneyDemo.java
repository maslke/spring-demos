package com.maslke.spring.juc;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TransferMoneyDemo {

    public static void main(String[] args) throws InterruptedException {
        Account[] accounts = new Account[10];
        for (int i = 0; i < 10; i++) {
            accounts[i] = new Account(i + 1, 1000);
        }
        Random rnd = new Random();
        CountDownLatch latch = new CountDownLatch(100);
        ExecutorService executor = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 100; i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    Account fromAccount = accounts[rnd.nextInt(10)];
                    Account toAccount = accounts[rnd.nextInt(10)];
                    transfer(fromAccount, toAccount, 204);
                    latch.countDown();
                }
            });
        }
        latch.await();
        for (int i = 0; i < 10; i++) {
            System.out.println("Id: " + accounts[i].getId() + ":" + accounts[i].getBalance());
        }
        executor.shutdown();


    }

    private static void transferHelper(Account fromAccount, Account toAccount, int amount) {
        int balance = fromAccount.getBalance();
        if (balance < amount) {
            System.out.println("fail, there is no enough money");
            return;
        }
        fromAccount.setBalance(balance - amount);
        toAccount.setBalance(toAccount.getBalance() + amount);
    }

    public static void transfer(final Account fromAccount, final Account toAccount, int amount) {
        System.out.println(String.format("%s->%s:%s", fromAccount.getId(), toAccount.getId(), amount));
        if (fromAccount == toAccount) return;
        if (fromAccount.getId() < toAccount.getId()) {
            synchronized (fromAccount) {
                synchronized (toAccount) {
                    transferHelper(fromAccount, toAccount, amount);
                }
            }

        }
        else {
            synchronized (toAccount) {
                synchronized (fromAccount) {
                    transferHelper(fromAccount, toAccount, amount);
                }
            }
        }
    }
}
