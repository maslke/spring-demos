package com.maslke.spring.juc.philosopereat;

import com.maslke.spring.juc.Philosopher;

import java.util.ArrayList;
import java.util.List;

public class DiningPhilosopherTimeoutProblem {
    public static void main(String[] args) {
        final int count = 10;
        List<Chopstick> chopsticks = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            chopsticks.add(new Chopstick(i));
        }
        List<Philosopher> philosophers = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            if (i != count - 1) {
                philosophers.add(new DeadlockingTimeoutPhilosopher(i, chopsticks.get(i), chopsticks.get(i + 1)));
            }
            else {
                philosophers.add(new DeadlockingTimeoutPhilosopher(i, chopsticks.get(i), chopsticks.get(0)));
            }
        }

        for (int i = 0; i < count; i++) {
            Thread t = (Thread) philosophers.get(i);
            t.start();
        }
    }
}
