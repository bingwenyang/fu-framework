package com.fu.core.dlock.service.impl;


import com.fu.core.dlock.entity.Lock;
import com.fu.core.dlock.service.IDistributedLockService;


public class DistributedLockService implements IDistributedLockService {

    IDistributedLockService distributedLock;


    public DistributedLockService(IDistributedLockService distributedLock) {
        this.distributedLock = distributedLock;
    }

    @Override
    public Lock newLock(String lockName) {

        return distributedLock.newLock(lockName);
    }

    @Deprecated
    public boolean lock(Lock lock) {
        return distributedLock.tryLock(lock);
    }

    @Override
    public boolean tryLock(Lock lock, long acquireTimeoutMs) {
        return distributedLock.tryLock(lock, acquireTimeoutMs);
    }


    @Override
    public boolean tryLock(Lock lock) {
        return distributedLock.tryLock(lock);
    }

    @Override
    public boolean unlock(Lock lock) {
        return distributedLock.unlock(lock);
    }


}
