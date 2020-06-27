package com.springcloud.thread.service;

import com.springcloud.thread.config.ThreadPoolExecutorUtil;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @description:
 * @author: Think
 * @date: 2020-06-27 09:35
 */
@Service
public class AsyncService {
    @Async("async-service")
    public void executeAsync() {
        System.err.println("开始执行任务");
        ThreadPoolExecutor threadPool = ThreadPoolExecutorUtil.getThreadPool();
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            threadPool.execute(()->{
                try {
                    Thread.sleep(100);
                    System.err.println("来了"+ finalI);
                } catch (InterruptedException e) {

                }
                System.err.println("当前执行任务的线程名称是"+Thread.currentThread().getName());
            });
        }
        threadPool.shutdown();
        System.err.println("任务结束");
    }
}
