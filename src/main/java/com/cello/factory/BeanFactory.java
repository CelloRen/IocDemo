package com.cello.factory;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public class BeanFactory {
    private static Properties properties;

    static {
        properties = new Properties();
        try {
            properties.load(BeanFactory.class.getClassLoader().getResourceAsStream("factory.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 通过反射实例化对象
    public static Object getDao(String beanName){
        String name = properties.getProperty(beanName);
        Object object = null;
        try {
            object = Class.forName(name).getConstructor().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return object;
    }
}
