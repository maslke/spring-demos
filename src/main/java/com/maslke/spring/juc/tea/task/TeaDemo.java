package com.maslke.spring.juc.tea.task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class TeaDemo {
    public static void main(String[] args) throws Exception {
        BoilWaterJob waterJob = new BoilWaterJob();
        FutureTask<Boolean> waterTask = new FutureTask<>(waterJob);
        CleanCupJob cupJob = new CleanCupJob();
        FutureTask<Boolean> cupTask = new FutureTask<>(cupJob);
        ExecutorService service = Executors.newFixedThreadPool(2);
        service.execute(waterTask);
        service.execute(cupTask);
        Boolean result = waterTask.get();
        Boolean result2 = cupTask.get();
        if (result && result2) {
            System.out.println("开始泡茶");
        }
    }
}
