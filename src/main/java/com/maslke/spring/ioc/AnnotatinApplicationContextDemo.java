package com.maslke.spring.ioc;

import com.maslke.spring.domain.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.AnnotationConfigRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class AnnotatinApplicationContextDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotatinApplicationContextDemo.class);

        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        beanDefinitionBuilder.addPropertyValue("name", "maslke2");
        beanDefinitionBuilder.addPropertyValue("id", "2000");
        BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();

        GenericBeanDefinition beanDefinition1 = new GenericBeanDefinition();
        beanDefinition1.setBeanClass(User.class);
        MutablePropertyValues propertyValues = new MutablePropertyValues();
        propertyValues.addPropertyValue("name", "maslke3");
        propertyValues.add("id", 2001);
        beanDefinition1.setPropertyValues(propertyValues);

        // 手动注册 BeanDefinition
        ((BeanDefinitionRegistry) applicationContext).registerBeanDefinition("user2", beanDefinition);
        ((BeanDefinitionRegistry) applicationContext).registerBeanDefinition("user3", beanDefinition1);

        applicationContext.refresh();
        lookupCollectionByType(applicationContext);
    }

    private static void lookupCollectionByType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            Map<String, User> map = ((ListableBeanFactory) beanFactory).getBeansOfType(User.class);
            System.out.println(map);
        }
    }

    @Bean("user4")
    public User getUser() {
        User user = new User();
        user.setName("ma");
        user.setId(100L);
        return user;
    }
}
