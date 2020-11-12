package com.lmn.rabbit.listener;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lmn.rabbit.entity.RemindInfo;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: Think
 * @date: 2019-12-22 22:32
 */
@Component
@RabbitListener(queues = "topic.debug")//监听的队列名称 TestDirectQueue
public class TopicDebugReceiver implements ChannelAwareMessageListener {
    private final static Logger logger = LoggerFactory.getLogger(TopicDebugReceiver.class);
    private ThreadLocal<List> listThreadLocal = new ThreadLocal<>();
    private List<RemindInfo> list = new ArrayList<>();

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            byte[] b = message.getBody();
            Object object = JSON.parseObject(b, RemindInfo.class);
            RemindInfo remindInfo = new ObjectMapper().convertValue(object, RemindInfo.class);
            list.add(remindInfo);
            JSONObject jsonObject = new JSONObject();
            String s = jsonObject.toJSONString(remindInfo);
            //listThreadLocal.set(list);
            Thread.sleep(1000);
            logger.info("接收debug消息,remindInfo>>>{}", s);
            logger.info("消息接收确认》》");
            channel.basicAck(deliveryTag, true);
        } catch (Exception e) {
            channel.basicReject(deliveryTag, false);
            e.printStackTrace();
        }
    }
}
