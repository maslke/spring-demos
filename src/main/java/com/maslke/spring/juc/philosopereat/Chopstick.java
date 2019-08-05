package com.maslke.spring.juc.philosopereat;

import com.maslke.spring.juc.Status;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Chopstick {
    public final int id;

    private Status status = Status.PUT_DOWN;

    private final ReentrantLock lock = new ReentrantLock();

    public Chopstick(int id) {
        super();
        this.id = id;
    }

    public void pickUp() {
        this.status = Status.PICKED_UP;
    }

    public void putDown() {
        this.status = Status.PUT_DOWN;
    }

    @Override
    public String toString() {
        return "Chopstick-" + id;
    }

    public boolean tryLock(long time, TimeUnit timeUnit) throws InterruptedException {
        return lock.tryLock(time, timeUnit);
    }

    public void unlock() {
        lock.unlock();
    }
}
