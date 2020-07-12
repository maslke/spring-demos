package com.maslke.spring.ioc;

import com.maslke.spring.repository.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class InjectionLookupDemo {
    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("spring/injection-context.xml");
        UserRepository userRepository = beanFactory.getBean("userRepository", UserRepository.class);
        System.out.println(userRepository.getUsers());

        System.out.println(userRepository.getBeanFactory() == beanFactory);

        System.out.println(userRepository.getApplicationContext() == beanFactory);
        System.out.println(userRepository.getBeanName());
    }

}
