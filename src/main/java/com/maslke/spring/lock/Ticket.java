package com.maslke.spring.lock;

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
        Thread t1 = new Thread(ticket, "A");
        Thread t2 = new Thread(ticket, "B");
        Thread t3 = new Thread(ticket, "C");
        t1.start();
        t2.start();
        t3.start();
    }
}
