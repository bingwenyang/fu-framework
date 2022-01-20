package com.fu.service.impl;

import com.fu.core.dlock.entity.Lock;
import com.fu.core.dlock.service.impl.DistributedLockService;
import com.fu.service.IZKDistributedLockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;


@Service
public class ZKDistributedLockServiceImpl implements IZKDistributedLockService {
    private static final Logger log = LoggerFactory.getLogger(ZKDistributedLockServiceImpl.class);
    public static final String LOCK_NAME = "/curator-lock101";
    @Autowired
    DistributedLockService distributedLockService;


    @Override
    public void testDistributedLockService(String lockName, Long acquireLockTimeout, TimeUnit unit) {
        for (int i = 0; i < 10; i++) {
            new Thread(new LockRunnable(LOCK_NAME, acquireLockTimeout, unit)).start();
        }
    }

    public class LockRunnable implements Runnable {
        String lockName;
        Long acquireLockTimeout;
        TimeUnit unit;

        public LockRunnable(String lockName, Long acquireLockTimeout, TimeUnit unit) {
            this.lockName = lockName;
            this.acquireLockTimeout = acquireLockTimeout;
            this.unit = unit;
        }

        @Override
        public void run() {
            boolean acquireLock;
            Lock lock = distributedLockService.newLock(LOCK_NAME);
            acquireLock = distributedLockService.tryLock(lock);
            // 获取锁失败
            if (!acquireLock) {
                log.info("获取锁失败, lockName:{}, threadName:{}", lockName, Thread.currentThread().getName());
                return;
            }
            log.info("获取锁成功, lockName:{}, threadName:{}", lockName, Thread.currentThread().getName());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                // ignore
            }

            // 释放锁
            boolean unlock = distributedLockService.unlock(lock);
            if (unlock) {
                log.info("释放锁成功, lockName:{}, threadName:{}", lockName, Thread.currentThread().getName());
            } else {
                log.info("释放锁失败, lockName:{}, threadName:{}", lockName, Thread.currentThread().getName());
            }
        }
    }
}
