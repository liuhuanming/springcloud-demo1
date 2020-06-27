package com.springcloud.thread.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: Think
 * @date: 2020-06-27 22:13
 */
@Data
@Component
@ConfigurationProperties(prefix = "task.pool")
public class AsyncTaskProperties {
    private int corePoolSize;

    private int maxPoolSize;

    private int keepAliveSeconds;

    private int queueCapacity;
}
