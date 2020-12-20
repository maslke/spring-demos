package com.maslke.spring;


import com.maslke.spring.config.AppConfig;
import com.maslke.spring.es.EsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * App
 */
public class App {

    @Autowired
    private EsDao esDao;

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        App app = new App();
        app.esDao.searchByState("IL");
    }
}
