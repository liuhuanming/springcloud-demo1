package com.springcloud.redis.lock;

import com.springcloud.redis.redisson.DistributedLocker;
import com.springcloud.redis.redisson.impl.RedisDistributedLocker;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: Think
 * @date: 2020-05-05 23:37
 */
@Component
public class RedisLockUtil {
    @Autowired
    public RedisDistributedLocker locker;

    private static DistributedLocker distributedLocker;

    @PostConstruct
    private void init() {
        distributedLocker = locker;
    }

    /**
     * 加锁
     * @param lockKey
     * @return
     */
    public RLock lock(String lockKey) {
        return distributedLocker.lock(lockKey);
    }

    /**
     * 释放锁
     * @param lockKey
     */
    public void unlock(String lockKey) {
        distributedLocker.unlock(lockKey);
    }

    /**
     * 释放锁
     * @param lock
     */
    public void unlock(RLock lock) {
        distributedLocker.unlock(lock);
    }

    /**
     * 带超时的锁
     * @param lockKey
     * @param timeout 超时时间   单位：秒
     */
    public RLock lock(String lockKey, int timeout) {
        return distributedLocker.lock(lockKey, timeout);
    }

    /**
     * 带超时的锁
     * @param lockKey
     * @param unit 时间单位
     * @param timeout 超时时间
     */
    public RLock lock(String lockKey, int timeout, TimeUnit unit ) {
        return distributedLocker.lock(lockKey, unit, timeout);
    }

    /**
     * 尝试获取锁
     * @param lockKey
     * @param waitTime 最多等待时间
     * @param leaseTime 上锁后自动释放锁时间
     * @return
     */
    public boolean tryLock(String lockKey, int waitTime, int leaseTime) {
        return distributedLocker.tryLock(lockKey, TimeUnit.SECONDS, waitTime, leaseTime);
    }

    /**
     * 尝试获取锁
     * @param lockKey
     * @param unit 时间单位
     * @param waitTime 最多等待时间
     * @param leaseTime 上锁后自动释放锁时间
     * @return
     */
    public boolean tryLock(String lockKey, TimeUnit unit, int waitTime, int leaseTime) {
        return distributedLocker.tryLock(lockKey, unit, waitTime, leaseTime);
    }

    public boolean isLock(String lockKey){
        return distributedLocker.isLock(lockKey);
    }

    public boolean isHeldByCurrentThread(String lockKey){
        return distributedLocker.isHeldByCurrentThread(lockKey);
    }

//    /**
//     * 获取计数器
//     *
//     * @param name
//     * @return
//     */
//    public RCountDownLatch getCountDownLatch(String name){
//        return distributedLocker.getCountDownLatch(name);
//    }
//
//    /**
//     * 获取信号量
//     *
//     * @param name
//     * @return
//     */
//    public RSemaphore getSemaphore(String name){
//        return distributedLocker.getSemaphore(name);
//    }
}
