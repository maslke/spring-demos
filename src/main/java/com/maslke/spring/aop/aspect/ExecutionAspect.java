package com.maslke.spring.aop.aspect;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class ExecutionAspect {

    @AfterReturning("execution(* greetTo(Integer))")
    public void beforeGreeting() {
        System.out.println("How are you,value");
    }
}
