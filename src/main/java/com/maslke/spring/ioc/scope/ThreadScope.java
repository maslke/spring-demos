package com.maslke.spring.ioc.scope;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

public class ThreadScope implements Scope {

    private final ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<Map<String, Object>>() {
        protected Object initValue() {
            return new HashMap<String, Object>();
        }
    };

    @Override
    public void registerDestructionCallback(String s, Runnable runnable) {

    }

    @Override
    public Object resolveContextualObject(String s) {
        return null;
    }

    @Override
    public String getConversationId() {
        return null;
    }

    @Override
    public Object get(String s, ObjectFactory<?> objectFactory) {
        Map scope = threadLocal.get();
        Object object = scope.get(s);
        if (object == null) {
            object = objectFactory.getObject();
            scope.put(s, object);
        }
        return object;
    }

    @Override
    public Object remove(String s) {
        Map scope = threadLocal.get();
        return scope.remove(s);
    }
}
