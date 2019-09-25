package com.maslke.spring.juc.tea.guava;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.maslke.spring.juc.tea.task.BoilWaterJob;
import com.maslke.spring.juc.tea.task.CleanCupJob;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GuavaTeaDemo extends Thread {
    private boolean waterOk = false;
    private boolean cupOk = false;

    private CountDownLatch latch = new CountDownLatch(2);

    public void setWaterOk(boolean waterOk) {
        this.waterOk = waterOk;
    }

    public void setCupOk(boolean cupOk) {
        this.cupOk = cupOk;
    }

    public boolean isWaterOk() {
        return waterOk;
    }

    public boolean isCupOk() {
        return cupOk;
    }

    public void messageNotify() {
        latch.countDown();
    }

    public void run() {
        try {
            latch.await();
            if (cupOk && waterOk) {
                System.out.println("开始泡茶");
            }
            else if (!cupOk) {
                System.out.println("清洗杯子失败");
            }
            else if (!waterOk) {
                System.out.println("烧水失败");
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        final GuavaTeaDemo demo = new GuavaTeaDemo();
        demo.start();
        ExecutorService service = Executors.newFixedThreadPool(2);
        ListeningExecutorService executorService = MoreExecutors.listeningDecorator(service);
        BoilWaterJob waterJob = new BoilWaterJob();
        CleanCupJob cupJob = new CleanCupJob();
        ListenableFuture<Boolean> future = executorService.submit(waterJob);
        Futures.addCallback(future, new FutureCallback<Boolean>() {
            public void onSuccess(@Nullable Boolean aBoolean) {
                demo.setWaterOk(aBoolean);
                demo.messageNotify();
            }

            public void onFailure(Throwable throwable) {
                throwable.printStackTrace();
            }
        }, executorService);
        ListenableFuture<Boolean> future2 = executorService.submit(cupJob);
        Futures.addCallback(future2, new FutureCallback<Boolean>() {
            public void onSuccess(@Nullable Boolean aBoolean) {
                demo.setCupOk(aBoolean);
                demo.messageNotify();
            }

            public void onFailure(Throwable throwable) {
                throwable.printStackTrace();
            }
        }, executorService);
    }
}
