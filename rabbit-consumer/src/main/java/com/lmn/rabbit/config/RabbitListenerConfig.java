package com.lmn.rabbit.config;

import com.lmn.rabbit.listener.TopicDebugReceiver;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @author: Think
 * @date: 2019-12-22 21:24
 */
@Configuration
public class RabbitListenerConfig {
    @Autowired
    private CachingConnectionFactory connectionFactory;
    @Autowired
    private TopicDebugReceiver topicDebugReceiver;

    @Bean
    public SimpleMessageListenerContainer simpleMessageListenerContainer() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.setConcurrentConsumers(1);
        container.setMaxConcurrentConsumers(1);
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL); // RabbitMQ默认是自动确认，这里改为手动确认消息
        // Receiver需要实现ChannelAwareMessageListener，对于消息进行确认
//        container.setQueueNames("queue_1");
//        container.setupMessageListener();

        container.setQueueNames("topic.debug");
        container.setMessageListener(topicDebugReceiver);
        return container;
    }
}
