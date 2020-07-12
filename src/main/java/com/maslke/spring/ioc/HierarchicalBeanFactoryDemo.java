package com.maslke.spring.ioc;

import com.maslke.spring.domain.SuperUser;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.HierarchicalBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

public class HierarchicalBeanFactoryDemo {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/lookup-context.xml");

        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.setParentBeanFactory(applicationContext);
        displayLocalBean(applicationContext, "user");
        displayLocalBean(beanFactory, "user");

        System.out.println(displayContainsBean(beanFactory, "user"));

        Map<String, SuperUser> map = BeanFactoryUtils.beansOfTypeIncludingAncestors(beanFactory, SuperUser.class);
        System.out.println(map);
    }

    private static boolean displayContainsBean(HierarchicalBeanFactory beanFactory, String beanName) {
        BeanFactory parentBeanFactory = beanFactory.getParentBeanFactory();
        if (parentBeanFactory instanceof HierarchicalBeanFactory) {
            HierarchicalBeanFactory hierarchicalBeanFactory = HierarchicalBeanFactory.class.cast(parentBeanFactory);
            return displayContainsBean(hierarchicalBeanFactory, beanName);
        }
        return beanFactory.containsLocalBean(beanName);
    }

    private static void displayLocalBean(HierarchicalBeanFactory beanFactory, String beanName) {
        System.out.println("当前应用上下文是否包含本地bean :" + beanFactory.containsLocalBean(beanName));
    }
}
