package com.maslke.spring.juc.tea.join;

public class JoinTeaDemo {
    public static void main(String[] args) throws Exception {
        final int SLEEP_TIME = 5400;
        Thread waterThread = new Thread(() -> {
            System.out.println("开始烧水");
            try {
                Thread.sleep(SLEEP_TIME);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
            System.out.println("水已烧好");
        });
        Thread cupThread = new Thread(() -> {
            System.out.println("开始清洗茶杯");
            try {
                Thread.sleep(SLEEP_TIME);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            System.out.println("茶杯已洗好");
        });
        waterThread.start();
        cupThread.start();
        waterThread.join();
        cupThread.join();
        System.out.println("准备就绪，开始泡茶");
    }
}
