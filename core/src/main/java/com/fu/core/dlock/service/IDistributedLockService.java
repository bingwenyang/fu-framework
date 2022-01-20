package com.fu.core.dlock.service;

import com.fu.core.dlock.entity.Lock;


public interface IDistributedLockService {


    /**
     * 获取锁
     *
     * @param lockName 锁的路径/标识
     * @return 锁对象
     */
    Lock newLock(String lockName);


    /**
     * 获取锁
     *
     * @param lock 锁的路径/标识
     * @return
     */
    boolean tryLock(Lock lock);


    /**
     * 获取锁
     *
     * @param lock             锁的路径/标识
     * @param acquireTimeoutMs 获取锁的超时时间（毫秒，小于等于0时，代表不断尝试获取锁，直到成功获取锁）
     * @return
     */
    boolean tryLock(Lock lock, long acquireTimeoutMs);



    /**
     * 释放锁
     *
     * @param lock 锁对象
     * @return
     */
    boolean unlock(Lock lock);



}
