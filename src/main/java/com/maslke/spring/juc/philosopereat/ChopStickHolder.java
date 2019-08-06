package com.maslke.spring.juc.philosopereat;

import java.util.ArrayList;
import java.util.List;

/**
 * 模拟一次获取两把锁
 */
public class ChopStickHolder {
    private static List<Chopstick> chopsticks = new ArrayList<>();

    private static final class ChopStickHolderHolder {
        private static final ChopStickHolder INSTANCE = new ChopStickHolder();
    }

    public static ChopStickHolder getInstance() {
        return ChopStickHolderHolder.INSTANCE;
    }

    public synchronized boolean acquire(Chopstick left, Chopstick right) {
        if (chopsticks.contains(left) || chopsticks.contains(right)) {
            return false;
        }
        chopsticks.add(left);
        chopsticks.add(right);
        return true;
    }

    public synchronized void acquire2(Chopstick left, Chopstick right) throws InterruptedException {
        while (chopsticks.contains(left) || chopsticks.contains(right)) {
            System.out.println(this + " is wating for " + left + " and " + right);
            wait();
        }
        chopsticks.add(left);
        chopsticks.add(right);
    }

    public synchronized void release2(Chopstick left, Chopstick right) {
        chopsticks.remove(left);
        chopsticks.remove(right);
        notify();
    }

    public synchronized void release(Chopstick left, Chopstick right) {
        chopsticks.remove(left);
        chopsticks.remove(right);
    }

}
