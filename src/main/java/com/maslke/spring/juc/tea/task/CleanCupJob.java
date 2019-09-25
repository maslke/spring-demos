package com.maslke.spring.juc.tea.task;

import java.util.concurrent.Callable;

public class CleanCupJob implements Callable<Boolean> {
    private static final int SLEEP_TIME = 5000;
    @Override
    public Boolean call() throws Exception {
        System.out.println("开始清洗杯子");
        try {
            Thread.sleep(SLEEP_TIME);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("杯子清洗完成");
        return true;
    }
}
