package com.springcloud.elasticsearch.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.springcloud.common.result.Response;
import com.springcloud.common.result.Result;
import com.springcloud.common.result.ResultEnum;
import com.springcloud.elasticsearch.entity.User;
import com.springcloud.elasticsearch.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description:
 * @author: lmn
 * @date: 2019-10-26 21:45
 */
@Api(value = "测试", tags = "测试用户模块")
@RequestMapping("/uTest")
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "添加用户")
    @PutMapping("/user")
    public Result<String> add(User user) {
        int i = userService.save(user);
        if (i > 0) {
            return Response.ok();
        }
        return Response.error(ResultEnum.ERROR);
    }

    @ApiOperation(value = "查询用户list")
    @GetMapping("/user")
    public Result<List<User>> list() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        List<User> userList = userService.selectList(queryWrapper);
        if (!userList.isEmpty()) {

            return Response.ok(userList);
        }
        return Response.error(ResultEnum.ERROR);
    }
}

