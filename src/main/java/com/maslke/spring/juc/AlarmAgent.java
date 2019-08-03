package com.maslke.spring.juc;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AlarmAgent {

    private static class AlarmAgentHolder {
        static AlarmAgent INSTANCE = new AlarmAgent();
    }

    private boolean connectedToServer = false;

    private ExecutorService executorService = Executors.newCachedThreadPool();

    private final HeartbeartThread heartbeartThread = new HeartbeartThread();

    private AlarmAgent() {

    }

    public static AlarmAgent getInstance() {
        return AlarmAgentHolder.INSTANCE;
    }

    public void init() {
        connectToServer();
        heartbeartThread.setDaemon(true);
        heartbeartThread.start();
    }

    private void connectToServer() {
        executorService.execute(this::doConnect);
    }

    private void doConnect() {
        Random random = new Random();
        synchronized (this) {
            connectedToServer = true;
            notify();
        }
    }

    public void sendAlarm(String message) throws InterruptedException {
        synchronized (this) {
            while (!connectedToServer) {
                System.out.println("Alarm agent was not connected to server.");
                wait();
            }
            Thread.sleep(1000);
            doSendAlarm(message);
        }
    }

    private void doSendAlarm(String message) {
        System.out.println("Alarm sent:" + message);
    }

    class HeartbeartThread extends Thread {
        @Override
        public void run() {
            try {
                while (true) {
                    if (checkConnection()) {
                        connectedToServer = true;
                    }
                    else {
                        connectedToServer = false;
                        System.out.println("Alarm agent was disconnected to server");
                        connectToServer();
                    }
                    Thread.sleep(2000);
                }
            }
            catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
        }

        private boolean checkConnection() {
            final Random random = new Random();
            int rand = random.nextInt(1000);
            return rand > 500;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AlarmAgent.getInstance().init();
        for (int i = 0; i < 100; i++) {
            AlarmAgent.getInstance().sendAlarm("messaage:" + i);
        }
        AlarmAgent.getInstance().executorService.shutdown();
    }
}


