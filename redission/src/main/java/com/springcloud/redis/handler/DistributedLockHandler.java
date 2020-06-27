package com.springcloud.redis.handler;

import com.springcloud.redis.annotation.DistributedLock;
import com.springcloud.redis.lock.RedisLockUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 分布式锁的实现
 * @author: Think
 * @date: 2020-06-15 23:37
 */
@Aspect
@Component
@Slf4j
public class DistributedLockHandler {
    @Autowired
    private RedisLockUtil redisLockUtil;

    @Around("@annotation(distributedLock)")
    public void around(ProceedingJoinPoint joinPoint, DistributedLock distributedLock) {
        log.info("锁的开始");
        // 获取锁的名称
        String lockKey = distributedLock.value();
        // 获取锁的超时时间
        int leaseTime = distributedLock.leaseTime();
        redisLockUtil.lock(lockKey, leaseTime);
        try {
            log.info("获取关于{}的分布式锁,开始执行业务", lockKey);
            joinPoint.proceed();
        } catch (Throwable throwable) {
            log.error("获取Redis分布式锁[异常]，加锁失败", throwable);
            throwable.printStackTrace();
        } finally {
            // 如果纯种持有该锁，就释放锁，如果该线程没有持有锁，说明锁到了过期时间，自动释放
            if (redisLockUtil.isHeldByCurrentThread(lockKey)) {
                redisLockUtil.unlock(lockKey);
            }
        }
        log.info("关于{}的分布式锁,执行业务结束",lockKey);
    }
}
