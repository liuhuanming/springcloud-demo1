package com.lmn.rabbit.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TopicErrorReceiver {
    @RabbitListener(queues = "topic.error") //监听的队列名称
    public void process(String msg) throws InterruptedException {
//        Thread.sleep(1000);
        System.out.println("接收error消息》》" + msg);
    }
}
