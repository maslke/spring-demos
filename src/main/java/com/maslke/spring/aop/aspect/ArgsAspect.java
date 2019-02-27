package com.maslke.spring.aop.aspect;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class ArgsAspect {

    @AfterReturning("execution(* greetTo(Integer)) && args(value)")
    public void beforeGreeting(int value) {
        System.out.println("How are you," + value);
    }
}
