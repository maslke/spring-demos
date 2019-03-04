package com.maslke.spring.reflect;

/**
 * @author:maslke
 * @date:3/4/2019
 * @version:0.0.1
 */
public class ClassLoaderTest {
    public static void main(String[] args) {
        //Root class loader||ext class loader || app class loader
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        System.out.println("current loader: " + loader);
        System.out.println("parent loader: " + loader.getParent());
        System.out.println("grandparent loader: " + loader.getParent().getParent());
    }
}
