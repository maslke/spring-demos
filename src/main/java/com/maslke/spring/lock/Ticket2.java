package com.maslke.spring.lock;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Ticket2 implements Runnable {
    private AtomicInteger number;

    public Ticket2(int num) {
        this.number = new AtomicInteger((num));
    }

    @Override
    public  void run() {
        while (this.number.intValue() > 0) {
            try {
                Thread.sleep(20);
            }
            catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
            System.out.println(Thread.currentThread().getName() + ":" + this.number.decrementAndGet());
        }


    }
}

class TicketTest2 {
    public static void main(String[] args) {
        Ticket ticket = new Ticket(1000);
        ExecutorService executor = Executors.newFixedThreadPool(3);
        executor.execute(ticket);
        executor.execute(ticket);
        executor.execute(ticket);
        executor.shutdown();
    }
}
