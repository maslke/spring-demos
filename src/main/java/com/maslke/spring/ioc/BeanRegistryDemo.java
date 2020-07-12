package com.maslke.spring.ioc;

import com.maslke.spring.domain.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

import java.util.Map;

//@Import(BeanRegistryDemo.Conf.class)
public class BeanRegistryDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(Conf.class);

        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        beanDefinitionBuilder.setFactoryMethod("createUser");
//        beanDefinitionBuilder.addPropertyValue("name", "maslke2");
//        beanDefinitionBuilder.addPropertyValue("id", "2000");
        BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        BeanDefinitionHolder holder = new BeanDefinitionHolder(beanDefinition, "user2");
        BeanDefinitionReaderUtils.registerBeanDefinition(holder, applicationContext);
        applicationContext.refresh();
        lookupCollectionByType(applicationContext, User.class);
        lookupCollectionByType(applicationContext, Conf.class);

        System.out.println("Spring 应用上下文将要销毁.");
        applicationContext.close();
        System.out.println("Spring 应用上下文销毁完成.");
    }

    @Component(value = "conf")
    public static class Conf {
        @Bean(value = "user4", initMethod = "init2", destroyMethod = "destroy2")
        public User getUser() {
            User user = new User();
            user.setName("ma");
            user.setId(100L);
            return user;
        }
    }

    private static void lookupCollectionByType(BeanFactory beanFactory, Class clzz) {
        if (beanFactory instanceof ListableBeanFactory) {
            Map<String, User> map = ((ListableBeanFactory) beanFactory).getBeansOfType(clzz);
            System.out.println(map);
        }
    }


}
