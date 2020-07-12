package com.maslke.spring.domain;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class User implements InitializingBean, DisposableBean {
    private String name;
    private Long id;


    public User() {
    }

    public static User createUser() {
        return new User();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Person[id=" + id + ",name=" + name + "]";
    }


    public void destroy() throws Exception {
        System.out.println("DisposableBean Spring bean正在销毁中...");
    }

    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean Spring bean正在初始化中...");
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("@PostConstruct Spring bean正在初始化中...");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("@PreDestroy Spring bean正在销毁中...");
    }

    public void init2() {
        System.out.println("正定义初始化方法 Spring bean正在初始化中...");
    }

    public void destroy2() {
        System.out.println("自定义销毁方法 Spring bean正在销毁中...");
    }

}
