package com.maslke.spring.aop;

import java.lang.reflect.Proxy;

public class UserSerivceTest {
    public static void main(String[] args) {
        UserService service = new UserServiceImpl();

        UserServiceHandler handler = new UserServiceHandler(service);

        UserService proxy = (UserService) Proxy.newProxyInstance(service.getClass().getClassLoader(), service.getClass().getInterfaces(), handler);
        String say = proxy.say();

        System.out.println(say);
    }
}
