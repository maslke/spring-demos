package com.maslke.spring.ioc.factorybean;

import com.maslke.spring.domain.User;
import org.springframework.beans.factory.FactoryBean;

public class UserFactoryBean implements FactoryBean<User> {

    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class<?> getObjectType() {
        return User.class;
    }

    public boolean isSingleton() {
        return true;
    }

    public User getObject() throws Exception {
        User user = new User();
        user.setId(id);
        user.setName(name);
        return user;
    }
}
