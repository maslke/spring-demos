package com.maslke.spring.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Ticket implements Runnable {
    private int number;

    public Ticket(int num) {
        this.number = num;
    }

    public void run() {
        synchronized (this) {
            while (this.number > 0) {
                try {
                    Thread.sleep(20);
                }
                catch (InterruptedException ex) {
                    System.out.println(ex.getMessage());
                }
                System.out.println(Thread.currentThread().getName() + ":" + this.number--);
            }
        }


    }
}

class TicketTest {
    public static void main(String[] args) {
        Ticket ticket = new Ticket(1000);
        ExecutorService executorService = new ThreadPoolExecutor(3, 3, 0, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(3));
        executorService.submit(ticket);
        executorService.submit(ticket);
        executorService.submit(ticket);
        executorService.shutdown();
    }
}
