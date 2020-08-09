package com.springcloud.thread.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @description: 异步任务线程池装配类
 * @author: Think
 * @date: 2020-06-27 22:00
 */
@Slf4j
@Configuration
public class AsyncTaskExecutePool implements AsyncConfigurer {

//    private final AsyncTaskProperties config;
//
//    public AsyncTaskExecutePool(AsyncTaskProperties config) {
//        this.config = config;
//    }

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //配置核心线程数
        executor.setCorePoolSize(5);
//        executor.setCorePoolSize(config.getCorePoolSize());
        //配置最大线程数
        executor.setMaxPoolSize(10);
//        executor.setMaxPoolSize(config.getMaxPoolSize());
        //配置队列大小
        executor.setQueueCapacity(100);
//        executor.setQueueCapacity(config.getQueueCapacity());
        //配置线程池中的线程的名称前缀
        executor.setThreadNamePrefix("async-service-");
        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //执行初始化
        executor.initialize();
        return executor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return (throwable, method, objects) -> {
            log.error("====" + throwable.getMessage() + "====", throwable);
            log.error("exception method:" + method.getName());
        };
    }
}
