package com.maslke.spring.aop.advice;

public abstract interface Configurable {
    void setAcitve(boolean active);

    default int init() {
        return 2;
    }
}
