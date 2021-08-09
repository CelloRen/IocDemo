package com.cello.spring.test;

import com.cello.spring.entity.Account;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {
    public static void main(String[] args) {
        // 加载IOC容器
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext("com.cello.spring.entity");
        Account account = (Account) applicationContext.getBean("account");
        System.out.println(account);
        // 打印目前容器中所有的bean
        for (String beanDefinitionName : applicationContext.getBeanDefinitionNames()) {
            System.out.println(beanDefinitionName);
        }
    }
}
