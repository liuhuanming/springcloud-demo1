package com.springcloud.user.controller;

import com.springcloud.common.result.Response;
import com.springcloud.common.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: lmn
 * @date: 2019-10-26 21:45
 */
@Api(value = "用户",tags = "用户模块")
@RequestMapping("/user")
@RestController
public class UserController {
    @Value("${server.port}")
    String port;

    @ApiOperation(value = "测试")
    @GetMapping("/list")
    public Result<String> list(){
        return Response.ok("111");
    }

    @ApiOperation(value = "根据id获取用户信息")
    @GetMapping("/{userId}")
    public String getBlogByUserId(@PathVariable("userId") Integer userId){
        return "服务者接收到了:" + userId + ",port:" + port;
    }
}
