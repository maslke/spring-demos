package com.maslke.spring.quartz;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;

/**
 * @author:maslke
 * @date:2/28/2019
 * @version:0.0.1
 */
@Test
public class MyJobTest {

    @Test
    public void jobBeanTest() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/jobs.xml");
    }

}