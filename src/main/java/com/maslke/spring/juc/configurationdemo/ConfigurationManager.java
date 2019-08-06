package com.maslke.spring.juc.configurationdemo;

import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public enum ConfigurationManager {
    INSTANCE;

    //protected final Set<ConfigEventListener> listeners = new HashSet<>();

    protected final CopyOnWriteArraySet<ConfigEventListener> listeners = new CopyOnWriteArraySet<>();

    public Configuration load(String name) {
        Configuration config = loadConfigurationFromDB(name);
        for (ConfigEventListener listener : listeners) {
            listener.onConfigLoaded(config);
        }
        return config;
    }

    private Configuration loadConfigurationFromDB(String name) {
        try {
            Random random = new Random();
            Thread.sleep(random.nextInt(400));
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
        Configuration configuration = new Configuration(name, 1);
        configuration.setProperty("name", "maslke");
        configuration.setProperty("age", "30");
        return configuration;
    }

    public synchronized void registerListener(ConfigEventListener listener) {
        listeners.add(listener);
    }

    public synchronized void removeListener(ConfigEventListener listener) {
        listeners.remove(listener);
    }

    public void update(String name, int newVersion, Map<String, String> properties) {
        for (ConfigEventListener listener : listeners) {
            listener.onConfigUpdated(name, newVersion, properties);
        }
    }
}
