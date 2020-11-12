package com.lmn.rabbit.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author Think
 * @Description //topic相关配置
 * @Date 2019/12/19 22:31
 * @Param
 * @return com.lmn.rabbit.config.RabbitTopicConfig
 **/
@Configuration
public class RabbitTopicConfig {
    private final static String info = "topic.info";
    private final static String debug = "topic.debug";
    private final static String error = "topic.error";

    @Bean
    public Queue infoQueue() {
        return new Queue(info);
    }

    @Bean
    public Queue debugQueue() {
        return new Queue(debug);
    }

    @Bean
    public Queue errorQueue() {
        return new Queue(error);
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange("topicExchange");
    }

    @Bean
    public Binding bindExchangeInfo() {
        return BindingBuilder.bind(infoQueue()).to(topicExchange()).with("topic.info");
    }

    @Bean
    public Binding bingExchangeError() {
        return BindingBuilder.bind(errorQueue()).to(topicExchange()).with("topic.error");
    }

    @Bean
    public Binding bingExchangeDebug() {
        return BindingBuilder.bind(debugQueue()).to(topicExchange()).with("topic.debug");
    }

//    @Bean
//    public Binding bingExchangeLog() {
//        return BindingBuilder.bind(errorQueue()).to(topicExchange()).with("topic.*");
//    }
}
