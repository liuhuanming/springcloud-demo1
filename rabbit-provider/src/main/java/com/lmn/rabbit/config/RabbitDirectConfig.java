package com.lmn.rabbit.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 * @Author Think
 * @Description Exchange策略
 * @Date  2019/12/8 21:44
 * @Param
 * @return com.lmn.rabbit.config.RabbitDirectConfig
 **/
@Configuration
public class RabbitDirectConfig {
    private final static String DIRECT_NAME = "direct";

    @Bean
    public Queue queue() {
        return new Queue("queue_1");
    }

    @Bean
    public DirectExchange directExchange() {
        // 交换策略名称，是否持久化，是否自动删除
        return new DirectExchange(DIRECT_NAME, false, false);
    }

    @Bean
    public Binding binding(Queue queue, DirectExchange directExchange) {
        return BindingBuilder.bind(queue).to(directExchange).with("direct");
    }

}
