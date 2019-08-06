package com.maslke.spring.juc.philosopereat;

public class HoldingTwoChopsticksOncePhilosopher extends AbstractPhilosopher {

    public HoldingTwoChopsticksOncePhilosopher(int id, Chopstick left, Chopstick right) {
        super(id, left, right);
    }

    @Override
    public void eat() {
        while (!ChopStickHolder.getInstance().acquire(left, right)) {
            System.out.println(this + " is wating for " + left + " and " + right);
        }
        left.pickUp();
        right.pickUp();
        System.out.println(this + " is picking up both " + left + " and " + right);
        doEat();
        ChopStickHolder.getInstance().release(left, right);
        left.putDown();
        right.putDown();
        System.out.println(this + " is putting down both " + left + " and " + right);
    }
}
