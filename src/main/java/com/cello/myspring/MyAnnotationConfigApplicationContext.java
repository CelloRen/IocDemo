package com.cello.myspring;

import com.cello.spring.entity.Account;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MyAnnotationConfigApplicationContext {
    private Map<String, Object> iocMap = new HashMap<>();

    public MyAnnotationConfigApplicationContext(String packageName) {
        // 获取原材料：beanName和对应的class
        Set<MyBeanDefinition> beanDefinitions = findBeanDefinitions(packageName);
        // 根据原材料创建对象, 并存入缓存
        createObject(beanDefinitions);
        // 自动装载
        autowireObject(beanDefinitions);
    }

    // 获取原材料
    private Set<MyBeanDefinition> findBeanDefinitions(String packageName) {
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

    public Object getBean(String beanName) {
        return iocMap.get(beanName);
    }

    private void createObject(Set<MyBeanDefinition> beanDefinitionSet) {
        beanDefinitionSet.forEach(beanDefinition -> {
            Class clazz = beanDefinition.getBeanClass();
            try {
                // 根据构造器创建对象
                Object object = clazz.getConstructor().newInstance();

                // 属性赋值
                for (Field declaredField : clazz.getDeclaredFields()) {
                    MyValue myValueAnnotation = declaredField.getAnnotation(MyValue.class);
                    if (myValueAnnotation != null) {
                        String value = myValueAnnotation.value();
                        String filedName = declaredField.getName();
                        String methodName = "set" + filedName.substring(0, 1).toUpperCase() + filedName.substring(1);
                        Method method = clazz.getMethod(methodName, declaredField.getType());
                        // 数据类型转化
                        Object val = convertValue(value, declaredField.getType().getName());
                        method.invoke(object, val);
                    }
                }
                // 存入缓存
                iocMap.put(beanDefinition.getBeanName(), object);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        });
    }

    private void autowireObject(Set<MyBeanDefinition> beanDefinitions) {
        beanDefinitions.forEach(beanDefinition -> {
            Class clazz = beanDefinition.getBeanClass();
            for (Field declaredField : clazz.getDeclaredFields()) {
                MyAutowired myAutowired = declaredField.getAnnotation(MyAutowired.class);
                if (myAutowired != null) {
                    MyQualifier myQualifier = declaredField.getAnnotation(MyQualifier.class);
                    if (myQualifier != null) {
                        try {
                            // byName
                            String beanName = myQualifier.value();
                            Object bean = iocMap.get(beanName);
                            String filedName = declaredField.getName();
                            String methodName = "set" + filedName.substring(0, 1).toUpperCase() + filedName.substring(1);
                            Method method = clazz.getMethod(methodName, declaredField.getType());
                            method.invoke(getBean(beanDefinition.getBeanName()), bean);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        } catch (NoSuchMethodException e) {
                            e.printStackTrace();
                        }
                    } else {
                        // byType
                    }
                }
            }
        });
    }

    private Object convertValue(String value, String typeName) {
        switch (typeName) {
            case "java.lang.Integer":
                return Integer.valueOf(value);
            case "java.lang.String":
                return value;
            case "java.lang.Float":
                return Float.valueOf(value);
            // and so on
        }
        return null;
    }

    public static void main(String[] args) {
        MyAnnotationConfigApplicationContext test = new MyAnnotationConfigApplicationContext("com.cello.spring");
        Account account = (Account) test.getBean("myAccount");
        System.out.println(account);
    }
}
