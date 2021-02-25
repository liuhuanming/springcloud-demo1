package com.lmn.mybatisplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.lmn.mybatisplus.dao.TestDao;
import com.lmn.mybatisplus.entity.Test;
import com.lmn.mybatisplus.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: Think
 * @date: 2019-12-24 22:46
 */
@Service
public class TestServiceImpl implements TestService {
    @Autowired
    private TestDao testDao;

    @Override
    public Test selectById(long id) {
        return testDao.selectById(id);
    }

    public List<Test> selectList(Wrapper<Test> wrapper) {
        return testDao.selectList(wrapper);
    }
}
