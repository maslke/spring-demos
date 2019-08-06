package com.maslke.spring.juc.philosopereat;

public class HoldingTwoChopsticksOnceWaitPhilosopher extends AbstractPhilosopher {

    public HoldingTwoChopsticksOnceWaitPhilosopher(int id, Chopstick left, Chopstick right) {
        super(id, left, right);
    }

    @Override
    public void eat() {
        try {
            ChopStickHolder.getInstance().acquire2(left, right);
            left.pickUp();
            right.pickUp();
            System.out.println(this + " is picking up both " + left + " and " + right);
            doEat();
            ChopStickHolder.getInstance().release2(left, right);
            left.putDown();
            right.putDown();
            System.out.println(this + " is putting down both " + left + " and " + right);
        }
        catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
