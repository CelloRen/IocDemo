package com.cello.factory;

import com.cello.dao.HelloDao;
import com.cello.dao.impl.HelloDaoImpl;

public class BeanFactory {
    public static HelloDao getDao(){
        return new HelloDaoImpl();
    }
}
