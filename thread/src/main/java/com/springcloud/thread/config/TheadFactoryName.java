package com.springcloud.thread.config;

import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description: 使用线程工厂，定义线程的名称
 * @author: Think
 * @date: 2020-06-27 22:21
 */
@Component
public class TheadFactoryName implements ThreadFactory {
    private static final AtomicInteger POOL_NUM = new AtomicInteger(1);
    private final ThreadGroup threadGroup;
    private final AtomicInteger threadNum = new AtomicInteger(1);
    private final String namePrefix;

    public TheadFactoryName() {
        this("async-service");
    }

    private TheadFactoryName(String name) {
        SecurityManager securityManager = System.getSecurityManager();
        threadGroup = (securityManager != null) ? securityManager.getThreadGroup() :
                Thread.currentThread().getThreadGroup();
        this.namePrefix = name + POOL_NUM.getAndIncrement();
    }

    @Override
    public Thread newThread(Runnable r) {
        // 此时线程池中的名字是 namePrefix+"-thread-"+ 这个线程池中的第几个线程
        Thread t = new Thread(
                threadGroup
                , r
                , namePrefix + "-thread-" + threadNum.getAndIncrement(),
                0);
        // 不设置守护线程
        if (t.isDaemon()) {
            t.setDaemon(false);
        }
        // 线程的优先级
        if (t.getPriority() != Thread.NORM_PRIORITY) {
            t.setPriority(Thread.NORM_PRIORITY);
        }
        return t;
    }
}
