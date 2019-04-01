package com.maslke.spring.ioc;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

public class BeanLifecycle {

    public static void main(String[] args) {
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        Resource resource = resourcePatternResolver.getResource("classpath:spring/beans.xml");
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        xmlBeanDefinitionReader.loadBeanDefinitions(resource);

        beanFactory.addBeanPostProcessor(new CarBeanPostProcessor());
        beanFactory.addBeanPostProcessor(new CarInstantiationAwarePostProcessor());
        Car car = beanFactory.getBean("car", Car.class);

        car.introduce();

        Car car2 = beanFactory.getBean("car", Car.class);

        System.out.println("car == car2: " + (car == car2));

        beanFactory.destroySingletons();
    }
}
