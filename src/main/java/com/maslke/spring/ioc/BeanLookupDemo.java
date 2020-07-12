package com.maslke.spring.ioc;

import com.maslke.spring.annotation.Super;
import com.maslke.spring.aop.advice.Waiter;
import com.maslke.spring.domain.User;
import com.maslke.spring.ioc.factorybean.UserFactoryBean;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.HierarchicalBeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Iterator;
import java.util.Map;
import java.util.ServiceLoader;

public class BeanLookupDemo {
    public static void main(String[] args) throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/lookup-context.xml");

        AutowireCapableBeanFactory beanFactory = applicationContext.getAutowireCapableBeanFactory();
        User testUser = beanFactory.createBean(User.class);
        System.out.println(testUser.getName());
        lookupCollectionByType(applicationContext);
        lookupByType(applicationContext);
        lookupCollectionByTypeAndAnnotation(applicationContext);

        User user4 = (User) applicationContext.getBean("user4");
        System.out.println(user4.getId());
        UserFactoryBean userFactoryBean = (UserFactoryBean) applicationContext.getBean("&user4");
        System.out.println(userFactoryBean.getObjectType());
        System.out.println(((User) userFactoryBean.getObject()).getId());

        ServiceLoader<Waiter> serviceLoader = applicationContext.getBean("waiterServiceLoader", ServiceLoader.class);
        Iterator<Waiter> iterator = serviceLoader.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    private static boolean containsBean(HierarchicalBeanFactory beanFactory, String beanName) {
        BeanFactory beanFactory1 = beanFactory.getParentBeanFactory();
        if (beanFactory1 instanceof HierarchicalBeanFactory) {
            HierarchicalBeanFactory beanFactory2 = HierarchicalBeanFactory.class.cast(beanFactory1);
            return containsBean(beanFactory2, beanName);
        }
        return beanFactory.containsBean(beanName);
    }


    private static void lookupByType(BeanFactory beanFactory) {
        User user = beanFactory.getBean(User.class);
        System.out.println("ByType:" + user);
    }

    private static void lookupCollectionByType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            Map<String, User> map = ((ListableBeanFactory) beanFactory).getBeansOfType(User.class);
            System.out.println(map);
        }
    }

    private static void lookupCollectionByTypeAndAnnotation(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            Map<String, Object> map = (((ListableBeanFactory) beanFactory).getBeansWithAnnotation(Super.class));
            System.out.println(map);
        }
    }

    private static void lookupRealTime(BeanFactory beanFactory) {
        User user = beanFactory.getBean("user", User.class);
        System.out.println("RealTime:" + user);
    }

    private static void lookupLazy(BeanFactory beanFactory) {
        ObjectFactory<User> objectFactory = (ObjectFactory<User>) beanFactory.getBean("objectFactory");
        System.out.println("lazy:" + objectFactory.getObject());
    }
}
