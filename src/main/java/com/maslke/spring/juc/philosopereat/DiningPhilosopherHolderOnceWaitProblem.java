package com.maslke.spring.juc.philosopereat;

import com.maslke.spring.juc.Philosopher;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用wait-notify来优化一次性获取锁操作
 */
public class DiningPhilosopherHolderOnceWaitProblem {
    public static void main(String[] args) {
        final int count = 100;
        List<Chopstick> chopsticks = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            chopsticks.add(new Chopstick(i));
        }
        List<Philosopher> philosophers = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            if (i != count - 1) {
                philosophers.add(new HoldingTwoChopsticksOnceWaitPhilosopher(i, chopsticks.get(i), chopsticks.get(i + 1)));
            }
            else {
                philosophers.add(new HoldingTwoChopsticksOnceWaitPhilosopher(i, chopsticks.get(i), chopsticks.get(0)));
            }
        }

        for (int i = 0; i < count; i++) {
            Thread t = (Thread) philosophers.get(i);
            t.start();
        }
    }
}
