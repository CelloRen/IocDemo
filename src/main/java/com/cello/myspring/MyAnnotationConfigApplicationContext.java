package com.cello.myspring;

import java.util.HashSet;
import java.util.Set;

public class MyAnnotationConfigApplicationContext {
    public MyAnnotationConfigApplicationContext(String packageName) {
        Set<MyBeanDefinition> beanDefinitions = findBeanDefinitions(packageName);
        beanDefinitions.forEach(bean -> System.out.println(bean));
    }

    // 获取原材料
    public Set<MyBeanDefinition> findBeanDefinitions(String packageName) {
        Set<MyBeanDefinition> beanDefinitionSet = new HashSet<>();
        //  遍历包中的类
        Set<Class<?>> classes = MyTools.getClasses(packageName);
        for (Class<?> clazz : classes) {
            MyComponent myComponentAnnotation = clazz.getAnnotation(MyComponent.class);
            //  找到@Component注解的类
            if (myComponentAnnotation != null) {
                // 获取beanName, 默认值时为类名的首字母小写
                String beanName = myComponentAnnotation.value();
                if (beanName == null || "".equals(beanName)) {
                    String className = clazz.getSimpleName();
                    beanName = className.substring(0, 1).toLowerCase() + className.substring(1);
                }
                // 将其封装为MyBeanDefinition，装载
                beanDefinitionSet.add(new MyBeanDefinition(beanName, clazz));
            }
        }

        return beanDefinitionSet;
    }

    public static void main(String[] args) {
        MyAnnotationConfigApplicationContext test = new MyAnnotationConfigApplicationContext("com.cello.spring");
    }
}
