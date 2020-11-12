package com.lmn.rabbit.controller;

import com.lmn.common.result.Response;
import com.lmn.common.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: lmn
 * @date: 2019-10-20 22:12
 */
@Api(value = "Rabbit", tags = {"Rabbit模块"})
@RestController
public class DemoController {
    @Autowired
    RabbitTemplate rabbitTemplate;

    @ApiOperation(value = "测试Rabbitmq使用Direct接收消息")
    @GetMapping(value = "/consumerDirectMsg", produces = {"application/json;charset=UTF-8"})
    public Result<String> consumer() {
        return Response.ok();
    }

}

