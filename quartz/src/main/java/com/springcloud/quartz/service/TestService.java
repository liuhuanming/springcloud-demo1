package com.springcloud.quartz.service;

import com.springcloud.quartz.entity.Test;

import java.util.List;

/**
 * @description:
 * @author: Think
 * @date: 2020-04-14 23:19
 */
public interface TestService {
    List<Test> findAll();
}
