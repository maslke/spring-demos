package com.maslke.spring.aop.aspect;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;

/**
 * @author:maslke
 * @date:2/27/2019
 * @version:0.0.1
 */
@Aspect
public class AnnotationAspect {
    @AfterReturning("@annotation(com.maslke.spring.aop.advice.NeedTest)")
    public void needTest() {
        System.out.println("need Test executed");
    }
}
