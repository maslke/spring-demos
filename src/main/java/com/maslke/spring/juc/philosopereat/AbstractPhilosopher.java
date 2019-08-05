package com.maslke.spring.juc.philosopereat;

import com.maslke.spring.juc.Philosopher;

import java.util.Random;

public abstract class AbstractPhilosopher extends Thread implements Philosopher {
    protected final int id;
    protected final Chopstick left;
    protected final Chopstick right;

    private final Random random = new Random();

    public AbstractPhilosopher(int id, Chopstick left, Chopstick right) {
        this.id = id;
        this.left = left;
        this.right = right;
    }

    @Override
    public void run() {
        while (true) {
            think();
            eat();
        }
    }

    @Override
    public abstract void eat();

    protected void doEat() {
        System.out.println(this + " is eating");
        randomPause();
    }

    private void randomPause() {
        try {
            Thread.sleep(random.nextInt(10));
        }
        catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void think() {
        System.out.println(this + " is thinking");
        randomPause();
    }

    @Override
    public String toString() {
        return "Philosopher-" + id;
    }
}
