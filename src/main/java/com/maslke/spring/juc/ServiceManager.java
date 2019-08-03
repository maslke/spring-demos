package com.maslke.spring.juc;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

public class ServiceManager {
    static volatile CountDownLatch latch;
    static Set<Service> services;

    public static void startServices() {
        services = getServices();
        for (Service service : services) {
            service.start();
        }
    }

    public static void main(String[] args) {
        ServiceManager.startServices();
        boolean allIsOk = ServiceManager.checkServiceStatus();
        if (allIsOk) {
            System.out.println("start successfully");
        } else {
            System.out.println("start failed.");
        }
    }

    public static boolean checkServiceStatus() {
        boolean allIsOk = true;
        try {
            latch.await();
        }
        catch (InterruptedException ex) {
            return false;
        }

        for (Service service : services) {
            if (!service.isStarted()) {
                allIsOk = false;
                break;
            }
        }
        return allIsOk;
    }

    static Set<Service> getServices() {
        latch = new CountDownLatch(3);
        HashSet<Service> services = new HashSet<Service>();
        services.add(new SampleServiceC(latch));
        services.add(new SampleServiceA(latch));
        services.add(new SampleServiceB(latch));
        return services;
    }
}

interface Service {
    boolean isStarted();

    void start();
    void stop();
}

class SampleServiceA extends AbstractService {

    public SampleServiceA(CountDownLatch latch) {
        super(latch);
    }

    @Override
    protected void doStart() throws Exception {
        System.out.println("service a begin to start");
        Thread.sleep(2000);
        started = true;
    }
}

class SampleServiceB extends AbstractService {

    public SampleServiceB(CountDownLatch latch) {
        super(latch);
    }

    @Override
    protected void doStart() throws Exception {
        System.out.println("service b begin to start");
        Thread.sleep(2000);
        Random random = new Random();
        started = random.nextBoolean();
    }
}

class SampleServiceC extends AbstractService {

    public SampleServiceC(CountDownLatch latch) {
        super(latch);
    }

    @Override
    protected void doStart() throws Exception {
        System.out.println("service begin to start");
        Thread.sleep(2000);
        Random random = new Random();
        started = random.nextBoolean();
    }
}

abstract class AbstractService implements Service {
    protected boolean started = false;
    protected final CountDownLatch latch;

    public AbstractService(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public boolean isStarted() {
        return started;
    }

    protected abstract void doStart() throws Exception;

    @Override
    public void start() {
        new ServiceStarter().start();
    }

    @Override
    public void stop() {
        //
    }

    class ServiceStarter extends Thread {
        @Override
        public void run() {
            final String serviceName = AbstractService.this.getClass().getSimpleName();
            System.out.println("Starting " + serviceName);
            try {
                doStart();
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            finally {
                latch.countDown();
                System.out.println("Done Starting " + serviceName);
            }
        }
    }
}


