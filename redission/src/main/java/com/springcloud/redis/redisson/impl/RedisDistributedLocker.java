package com.springcloud.redis.redisson.impl;

import com.springcloud.redis.redisson.DistributedLocker;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: Think
 * @date: 2020-05-05 23:40
 */
@Component
public class RedisDistributedLocker implements DistributedLocker {

    @Autowired
    private RedissonClient redissonClient;

    /**
     * 加锁操作 (锁有效时间采用默认时间30秒）
     *
     * @param lockKey
     * @return
     */
    @Override
    public RLock lock(String lockKey) {
        RLock lock = redissonClient.getLock(lockKey);
        lock.lock();
        return lock;
    }

    /**
     * 加锁设置，设置锁的有效时间
     *
     * @param lockKey   锁名称
     * @param leaseTime 锁有效时间
     * @return
     */
    @Override
    public RLock lock(String lockKey, int leaseTime) {
        RLock lock = redissonClient.getLock(lockKey);
        lock.lock(leaseTime, TimeUnit.SECONDS);
        return lock;
    }

    /**
     * 加锁操作
     *
     * @param lockKey 锁名称
     * @param unit    单位
     * @param timeout 超时时间
     * @return
     */
    @Override
    public RLock lock(String lockKey, TimeUnit unit, int timeout) {
        RLock lock = redissonClient.getLock(lockKey);
        lock.lock(timeout, unit);
        return lock;
    }

    /**
     * 加锁操作(tryLock锁，有等待时间）
     *
     * @param lockKey   锁名称
     * @param unit      单位
     * @param waitTime  等待时间
     * @param leaseTime 锁有效时间
     * @return
     */
    @Override
    public boolean tryLock(String lockKey, TimeUnit unit, int waitTime, int leaseTime) {
        RLock lock = redissonClient.getLock(lockKey);
        try {
            return lock.tryLock(waitTime, leaseTime, unit);
        } catch (InterruptedException e) {
            return false;
        }
    }

    /**
     * 解锁操作
     *
     * @param lockKey
     */
    @Override
    public void unlock(String lockKey) {
        RLock lock = redissonClient.getLock(lockKey);
        lock.unlock();
    }

    /**
     * 解锁操作
     *
     * @param lock
     */
    @Override
    public void unlock(RLock lock) {
        lock.unlock();
    }

    /**
     * 判断该锁是否已经被线程持有
     *
     * @param lockKey 锁名称
     * @return 是否
     */
    @Override
    public boolean isLock(String lockKey) {
        RLock rLock = redissonClient.getLock(lockKey);
        return rLock.isLocked();
    }

    /**
     * 判断该线程是否持有当前锁
     *
     * @param lockKey 锁名称
     * @return 是否
     */
    @Override
    public boolean isHeldByCurrentThread(String lockKey) {
        RLock rLock = redissonClient.getLock(lockKey);
        return rLock.isHeldByCurrentThread();
    }
}
