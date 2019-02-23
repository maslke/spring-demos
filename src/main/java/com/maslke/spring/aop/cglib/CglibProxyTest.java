package com.maslke.spring.aop.cglib;

import com.maslke.spring.aop.jdk.UserService;
import com.maslke.spring.aop.jdk.UserServiceImpl;

public class CglibProxyTest {
    public static void main(String[] args) {
        CglibProxy proxy = new CglibProxy();
        UserService service = (UserService) proxy.getProxy(UserServiceImpl.class);

        System.out.println(service.say());
    }
}
