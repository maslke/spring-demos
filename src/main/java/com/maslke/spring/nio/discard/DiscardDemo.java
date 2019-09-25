package com.maslke.spring.nio.discard;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DiscardDemo {
    public static void main(String[] args) throws Exception {
        final String host = "127.0.0.1";
        final int port =  3232;

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        CountDownLatch latch = new CountDownLatch(1);
        executorService.execute(() -> {
            try {
                DiscardServer server = new DiscardServer(host, port);
                server.listen();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            } finally {
                latch.countDown();
            }
        });
        executorService.execute(() -> {
            try {
                latch.await();
                DiscardClient client = new DiscardClient(host, port);
                client.connect();
                while (true) {
                    client.send("hello world");
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        });



    }
}
