package com.maslke.spring.aop.advisor.staticmethodmatcher;

import java.lang.reflect.Method;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;

import com.maslke.spring.aop.advisor.Waiter2;

public class GreetingAdvisor extends StaticMethodMatcherPointcutAdvisor {
    @Override
    public ClassFilter getClassFilter() {
        return new ClassFilter() {
            @Override
            public boolean matches(Class<?> aClass) {
                return Waiter2.class.isAssignableFrom(aClass);
            }
        };
    }

    @Override
    public boolean matches(Method method, Class<?> aClass) {
        return "greetTo".equals(method.getName());
    }
}
