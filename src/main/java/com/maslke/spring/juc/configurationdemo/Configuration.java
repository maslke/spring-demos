package com.maslke.spring.juc.configurationdemo;

import java.util.HashMap;
import java.util.Map;

public class Configuration {
    private final String name;
    private volatile int version;

    private volatile Map<String, String> configItemMap;

    public Configuration(String name, int version) {
        this.name = name;
        this.version = version;
        configItemMap = new HashMap<>();
    }

    public void setProperty(String key, String value) {
        configItemMap.put(key, value);
    }

    public String getProperty(String key) {
        return configItemMap.get(key);
    }

    public void upate(Map<String, String> properties) {
        this.configItemMap = properties;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getVersion() {
        return this.version;
    }

    public String getName() {
        return this.name;
    }
}
