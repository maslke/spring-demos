package com.maslke.spring.ioc;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

public class CarFactoryPostProcessor implements BeanFactoryPostProcessor {
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        BeanDefinition bf = configurableListableBeanFactory.getBeanDefinition("car");

        bf.getPropertyValues().addPropertyValue("brand", "qq");
        System.out.println("BeanFactoryPostProcessor.postProcessBeanFactory");

    }
}
