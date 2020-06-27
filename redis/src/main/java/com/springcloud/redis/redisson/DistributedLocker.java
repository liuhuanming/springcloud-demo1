package com.springcloud.redis.redisson;

import org.redisson.api.RLock;

import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: Think
 * @date: 2020-05-05 23:39
 */
public interface DistributedLocker {
    RLock lock(String lockKey);

    RLock lock(String lockKey, int timeout);

    RLock lock(String lockKey, TimeUnit unit, int timeout);

    boolean tryLock(String lockKey, TimeUnit unit, int waitTime, int leaseTime);

    void unlock(String lockKey);

    void unlock(RLock lock);

    boolean isLock(String lockKey);

    boolean isHeldByCurrentThread(String lockKey);

}
