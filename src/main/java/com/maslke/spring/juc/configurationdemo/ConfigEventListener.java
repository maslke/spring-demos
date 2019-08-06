package com.maslke.spring.juc.configurationdemo;

import java.util.Map;

public interface ConfigEventListener {
    void onConfigLoaded(Configuration configuration);

    void onConfigUpdated(String name, int newVersion, Map<String, String> properties);
}
