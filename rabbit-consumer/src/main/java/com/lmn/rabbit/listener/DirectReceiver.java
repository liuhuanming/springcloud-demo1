package com.lmn.rabbit.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class DirectReceiver {
    @RabbitListener(queues = "queue_1") //监听的队列名称
    public void process(String msg) throws InterruptedException {
//        Thread.sleep(1000);
        System.out.println("接收消息》》" + msg);
    }
}
