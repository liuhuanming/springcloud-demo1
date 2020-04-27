package com.springcloud.quartz.service.impl;

import com.springcloud.quartz.dao.TestDao;
import com.springcloud.quartz.entity.Test;
import com.springcloud.quartz.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: Think
 * @date: 2020-04-14 23:19
 */
@Service
public class TestServiceImpl implements TestService {
    @Autowired
    private TestDao testDao;

    @Override
    public List<Test> findAll() {
        return testDao.findAll();
    }

}
