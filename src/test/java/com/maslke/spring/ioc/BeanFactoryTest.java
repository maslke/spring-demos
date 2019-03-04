package com.maslke.spring.ioc;

import com.maslke.spring.reflect.Car;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.testng.annotations.Test;

/**
 * @author:maslke
 * @date:3/4/2019
 * @version:0.0.1
 */
@Test
public class BeanFactoryTest {

    @Test
    public void getBeanTest() throws Exception {
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        Resource resource = resourcePatternResolver.getResource("classpath:spring/beans.xml");
        System.out.println(resource.getURL());

        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        BeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        beanDefinitionReader.loadBeanDefinitions(resource);

        Car car = beanFactory.getBean("car", Car.class);
        System.out.println(car.getBrand());
    }
}
