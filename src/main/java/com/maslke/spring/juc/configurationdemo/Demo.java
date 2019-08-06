package com.maslke.spring.juc.configurationdemo;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Demo {
    final static ConfigurationHelper configHelper = ConfigurationHelper.INSTANCE.init();

    public static void main(String[] args) throws InterruptedException {
        Thread trxThread = new Thread(new Runnable() {
            @Override
            public void run() {
                Configuration cfg = configHelper.getConfig("serverInfo");
                String name = cfg.getProperty("name");
                System.out.println("processing : " + name);
            }
        });

        Thread updateThread = new Thread(new Runnable() {
            @Override
            public void run() {
                Random random = new Random();
                try {
                    Thread.sleep(random.nextInt(40));
                } catch (InterruptedException ex) {
                    System.out.println(ex.getMessage());
                }

                Map<String, String> props = new HashMap<>();
                props.put("k1", "v1");
                props.put("k2", "v2");
                props.put("k3", "v3");
                ConfigurationManager.INSTANCE.update("anotherConfig", 2, props);
            }
        });

        trxThread.start();
        updateThread.start();
    }
}
