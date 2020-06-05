package com.springcloud.jpa.controller;

import com.springcloud.common.result.Response;
import com.springcloud.common.result.Result;
import com.springcloud.jpa.dao.UserDao;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: Think
 * @date: 2020-05-05 21:24
 */
@Api(value = "jpa模块", tags = "jpa模块")
@RequestMapping("/jpa")
@RestController
public class JPAController {
    @Autowired
    private UserDao userDao;

    @ApiOperation("测试")
    @GetMapping
    public Result test() {
        return Response.ok();
    }

    @ApiOperation("测试")
    @GetMapping("/list")
    public Result getUserList() {
        return Response.ok(userDao.findAll());
    }

}
