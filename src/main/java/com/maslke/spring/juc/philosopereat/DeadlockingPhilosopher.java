package com.maslke.spring.juc.philosopereat;

public class DeadlockingPhilosopher extends AbstractPhilosopher {

    public DeadlockingPhilosopher(int id, Chopstick left, Chopstick right) {
        super(id, left, right);
    }

    @Override
    public void eat() {
        synchronized (left) {
            left.pickUp();
            System.out.println(this + " is picking up " + left + " on his left");
            synchronized (right) {
                right.pickUp();
                System.out.println(this + " is picking up " + right + " on his right");
                doEat();
                right.putDown();
                System.out.println(this + " is putting down " + right + " on his right");
            }
            left.putDown();
            System.out.println(this + "is putting down " + left + " on his left");
        }
    }
}
