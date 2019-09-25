package com.maslke.spring.juc.tea.task;

import java.util.concurrent.Callable;

public class BoilWaterJob implements Callable<Boolean> {
    private static final int SLEEP_TIME = 5000;

    @Override
    public Boolean call() throws Exception {
        try {
            System.out.println("开始向壶中放水");
            System.out.println("开始烧水");
            Thread.sleep(SLEEP_TIME);
            System.out.println("水已烧开");
        }
        catch (InterruptedException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }
}
