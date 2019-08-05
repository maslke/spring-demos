package com.maslke.spring.juc.philosopereat;

/**
 * 根据Chopstick的id，从id小的开始获取锁。按照固定的顺序
 */
public class OrderLockPhilosopher extends AbstractPhilosopher {
    public OrderLockPhilosopher(int id, Chopstick left, Chopstick right) {
        super(id, left, right);
    }

    @Override
    public void eat() {
        final Chopstick first = left.id < right.id ? left : right;
        final Chopstick second = left.id < right.id ? right : left;
        final String firstPickDirection = first == left ? "left" : "right";
        final String secondPckDirection = second == right ? "right" : "left";


        synchronized (first) {
            first.pickUp();
            System.out.println(this + " is picking up " + first + " on his " + firstPickDirection);
            synchronized (second) {
                second.pickUp();
                System.out.println(this + " is picking up " + second + " on his " + secondPckDirection);
                doEat();
                second.putDown();
                System.out.println(this + " is putting down " + second + " on his " + secondPckDirection);
            }
            first.putDown();
            System.out.println(this + " is putting down " + first + " on his " + firstPickDirection);
        }
    }
}
