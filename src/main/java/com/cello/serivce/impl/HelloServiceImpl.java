package com.cello.serivce.impl;

import com.cello.dao.HelloDao;
import com.cello.factory.BeanFactory;
import com.cello.serivce.HelloService;

import java.util.List;

public class HelloServiceImpl implements HelloService {
    private HelloDao helloDao = BeanFactory.getDao();

    @Override
    public List<String> findAll() {
        return helloDao.findAll();
    }
}
