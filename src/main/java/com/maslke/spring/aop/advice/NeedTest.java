package com.maslke.spring.aop.advice;

/**
 * @author:maslke
 * @date:2/27/2019
 * @version:0.0.1
 */
public @interface NeedTest {
    boolean value() default true;
}
