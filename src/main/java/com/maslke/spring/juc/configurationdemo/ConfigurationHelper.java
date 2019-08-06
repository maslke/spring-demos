package com.maslke.spring.juc.configurationdemo;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public enum  ConfigurationHelper implements ConfigEventListener {
    INSTANCE;

    final ConfigurationManager configManager;
    final ConcurrentMap<String, Configuration> cachedConfig;

    private ConfigurationHelper() {
        this.configManager = ConfigurationManager.INSTANCE;
        this.cachedConfig = new ConcurrentHashMap<>();
    }

    public Configuration getConfig(String name) {
        Configuration cfg;
        cfg = getCachedConfig(name);
        if (null == cfg) {
            synchronized (this) {
                cfg = getCachedConfig(name);
                if (cfg == null) {
                    cfg = configManager.load(name);
                }
                cachedConfig.put(name, cfg);
            }
        }
        return cfg;
    }

    public Configuration getCachedConfig(String name) {
        return cachedConfig.get(name);
    }

    public ConfigurationHelper init() {
        configManager.registerListener(this);
        return this;
    }

    @Override
    public void onConfigLoaded(Configuration configuration) {
        cachedConfig.putIfAbsent(configuration.getName(), configuration);
    }

    @Override
    public void onConfigUpdated(String name, int newVersion, Map<String, String> properties) {
        Configuration cachedConfig = getCachedConfig(name);
        synchronized (this) {
            if (null != cachedConfig) {
                cachedConfig.upate(properties);
                cachedConfig.setVersion(newVersion);
            }
        }
    }
}
