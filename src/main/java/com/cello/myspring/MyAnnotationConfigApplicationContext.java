package com.cello.myspring;

import java.util.Set;

public class MyAnnotationConfigApplicationContext {
    public MyAnnotationConfigApplicationContext(String packageName) {
        Set<MyBeanDefinition> beanDefinitions = findBeanDefinitions(packageName);
    }

    // 获取原材料
    public Set<MyBeanDefinition> findBeanDefinitions(String packageName) {
        //  遍历包中的类
        Set<Class<?>> classes = MyTools.getClasses(packageName);
        for (Class clazz : classes) {

        }

        //  找到@Component注解的类


        //  将其封装为MyBeanDefinition，装载

        return null;
    }

    public static void main(String[] args) {
        MyAnnotationConfigApplicationContext test = new MyAnnotationConfigApplicationContext("com.cello.myspring");

    }
}