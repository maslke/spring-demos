package com.maslke.spring.juc.philosopereat;

import java.util.concurrent.TimeUnit;

/**
 * 获取锁的时候，增加了超时判断
 */
public class DeadlockingTimeoutPhilosopher extends AbstractPhilosopher {


    public DeadlockingTimeoutPhilosopher(int id, Chopstick left, Chopstick right) {
        super(id, left, right);
    }

    @Override
    public void eat() {
        final long time = 1000;
        try {
            if (left.tryLock(time, TimeUnit.MILLISECONDS)) {
                try {
                    System.out.println(this + " is picking up " + left + " on his left");
                    if (right.tryLock(time, TimeUnit.MILLISECONDS)) {
                        try {
                            System.out.println(this + " is picking up " + right + " on his right");
                            doEat();
                            System.out.println(this + " is putting down " + right + " on his right");
                        }
                        finally {
                            right.unlock();
                        }
                    }
                    else {
                        System.out.println(this + " can't pick up " + right + " on his right");
                    }
                    System.out.println(this + " is putting down " + left + " on his left");
                }
                finally {
                    left.unlock();
                }
            }
            else {
                System.out.println(this + " can't pick up " + left + " on his left");
            }
        }
        catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            System.out.println(ex.getMessage());
        }

    }
}
