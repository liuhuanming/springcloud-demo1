package com.lmn.mybatisplus.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.lmn.mybatisplus.entity.Test;

import java.util.List;

/**
 * @description:
 * @author: Think
 * @date: 2019-12-24 22:26
 */
public interface TestService {
    Test selectById(long id);

    List<Test> selectList(Wrapper<Test> wrapper);
}
