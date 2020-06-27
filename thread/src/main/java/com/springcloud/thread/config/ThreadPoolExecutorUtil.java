package com.springcloud.thread.config;

import com.springcloud.common.spring.SpringBeanTools;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @description: 获取自定义线程池
 * @author: Think
 * @date: 2020-06-27 20:07
 */
public class ThreadPoolExecutorUtil {
    public static ThreadPoolExecutor getThreadPool() {
        AsyncTaskProperties properties = SpringBeanTools.getBean(AsyncTaskProperties.class);
        return new ThreadPoolExecutor(
                properties.getCorePoolSize(),
                properties.getMaxPoolSize(),
                properties.getKeepAliveSeconds(),
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(properties.getQueueCapacity()),
                new TheadFactoryName()
        );
    }
}
