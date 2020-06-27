package com.springcloud.redis.annotation;

import java.lang.annotation.*;

/**
 * @description: 注解分布式锁
 * @author: Think
 * @date: 2020-06-15 23:33
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface DistributedLock {
    // 锁的默认名称
    String value() default "redisson";

    // 锁的默认有效时间
    int leaseTime() default 10;
}
