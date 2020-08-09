package com.springcloud.thread.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @description: 测试异步线程任务
 * @author: Think
 * @date: 2020-06-27 22:45
 */
@Slf4j
@Component
public class TestTask implements Runnable {
    public void run() {
        log.info("执行成功");
    }

    public void run1(String s) {
        log.info("执行任务，参数为{}", s);
    }
}
