package com.lmn.rabbit.controller;

import com.alibaba.fastjson.JSON;
import com.lmn.common.result.Response;
import com.lmn.common.result.Result;
import com.lmn.rabbit.entity.RemindInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

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

    @ApiOperation(value = "测试Rabbitmq使用Direct发送消息")
    @GetMapping(value = "/sendDirectMsg", produces = {"application/json;charset=UTF-8"})
    public Result<String> store() throws UnsupportedEncodingException {
        for (int i = 0; i < 1000; i++) {
            byte[] data = getData();
            rabbitTemplate.convertAndSend("direct", "direct", data);
        }
        return Response.ok();
    }

    @ApiOperation(value = "测试Rabbitmq使用Topic发送消息,主题是info")
    @GetMapping(value = "/sendTopicInfo", produces = {"application/json;charset=UTF-8"})
    public Result<String> sendInfo() throws UnsupportedEncodingException {
        for (int i = 0; i < 1000; i++) {
            byte[] data = getData();
            rabbitTemplate.convertAndSend("topicExchange", "topic.info", data);
        }
        return Response.ok();
    }

    @ApiOperation(value = "测试Rabbitmq使用Topic发送消息,主题是debug")
    @GetMapping(value = "/sendTopicDebug", produces = {"application/json;charset=UTF-8"})
    public Result<String> sendDebug() throws UnsupportedEncodingException {
        for (int i = 0; i < 1000; i++) {
            byte[] data = getData();
            rabbitTemplate.convertAndSend("topicExchange", "topic.debug", data);
        }
        return Response.ok();
    }

    @ApiOperation(value = "测试Rabbitmq使用Topic发送消息,主题是error")
    @GetMapping(value = "/sendTopicError", produces = {"application/json;charset=UTF-8"})
    public Result<String> sendError() throws UnsupportedEncodingException {
        for (int i = 0; i < 1000; i++) {
            byte[] data = getErrorData();
            rabbitTemplate.convertAndSend("topicExchange", "topic.error", data);
        }
        return Response.ok();
    }


    private byte[] getData() throws UnsupportedEncodingException {
        String uuid = String.valueOf(UUID.randomUUID());
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        RemindInfo remindInfo = new RemindInfo();
        remindInfo.setMessageId(uuid);
        remindInfo.setCreateTime(createTime);
        remindInfo.setMessage("我是一个大笨蛋-info");
        remindInfo.setSenderName("INFO");
        remindInfo.setToName("错过就是错过");
        String info = JSON.toJSONString(remindInfo);
        byte[] sendBytes = info.getBytes("UTF-8");
        return sendBytes;
    }

    private byte[] getErrorData() throws UnsupportedEncodingException {
        String uuid = String.valueOf(UUID.randomUUID());
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        RemindInfo remindInfo = new RemindInfo();
        remindInfo.setMessageId(uuid);
        remindInfo.setCreateTime(createTime);
        remindInfo.setMessage("我是ERROR");
        remindInfo.setSenderName("我应该两个都接收");
        remindInfo.setToName("我应该两个都接收");
        String info = JSON.toJSONString(remindInfo);
        byte[] sendBytes = info.getBytes("UTF-8");
        return sendBytes;
    }

}

