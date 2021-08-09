package com.cello.serivce.impl;

import com.cello.dao.HelloDao;
import com.cello.dao.impl.HelloDaoImpl2;
import com.cello.serivce.HelloService;

import java.util.List;

public class HelloServiceImpl implements HelloService {
    private HelloDao helloDao = new HelloDaoImpl2();

    @Override
    public List<String> findAll() {
        return helloDao.findAll();
    }
}
