package com.maslke.spring.lock;

public class SingleInstance {

    static class Single {
        public static SingleInstance instance = new SingleInstance();
    }

    public static SingleInstance getInstance() {
        return Single.instance;
    }
}
