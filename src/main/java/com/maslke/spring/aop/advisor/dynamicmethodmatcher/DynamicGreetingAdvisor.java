package com.maslke.spring.aop.advisor.dynamicmethodmatcher;

import com.maslke.spring.aop.advisor.Waiter2;
import java.lang.reflect.Method;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.DynamicMethodMatcherPointcut;

/**
 * @author:maslke
 * @date:2/25/2019
 * @version:0.0.1
 */
public class DynamicGreetingAdvisor extends DynamicMethodMatcherPointcut {
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
    public boolean matches(Method method, Class<?> targetClass) {
        System.out.println("static matcher:" + method.getName());
        return "greetTo".equals(method.getName());
    }

    @Override
    public boolean matches(Method method, Class<?> aClass, Object... objects) {
        System.out.println("dynamic matcher:" + method.getName());
        String clientName = (String) objects[0];
        return "maslke".equals(clientName);
    }
}
