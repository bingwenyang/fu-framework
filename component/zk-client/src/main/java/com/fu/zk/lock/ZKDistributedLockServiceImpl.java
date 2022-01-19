package com.fu.zk.lock;

import com.fu.core.dlock.entity.Lock;
import com.fu.core.dlock.service.IDistributedLockService;
import org.apache.commons.lang3.StringUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;


public class ZKDistributedLockServiceImpl implements IDistributedLockService {

    private static final Logger log = LoggerFactory.getLogger(ZKDistributedLockServiceImpl.class);

    private final CuratorFramework curatorFramework;

    public ZKDistributedLockServiceImpl(CuratorFramework curatorFramework) {
        this.curatorFramework = curatorFramework;
    }


    @Override
    public Lock newLock(String lockName) {
        Lock zkLock = new Lock(lockName);
        InterProcessMutex interProcessMutex = new InterProcessMutex(curatorFramework, lockName);
        zkLock.setLock(interProcessMutex);
        return zkLock;
    }


    @Override
    public boolean tryLock(Lock lock) {
        return tryLock(lock, -1);
    }

    @Override
    public boolean tryLock(Lock lock, long acquireTimeoutMs) {
        boolean result = false;
        try {
            result = ((InterProcessMutex) lock.getLock()).acquire(acquireTimeoutMs <= 0 ? -1 : acquireTimeoutMs, acquireTimeoutMs <= 0 ? null : TimeUnit.MILLISECONDS);
            if (result) {
                lock.success();
            }
        } catch (Exception e) {
            log.error("获取锁[{}]失败,error:", lock.getName(), e);
            return result;
        }
        return result;
    }


    @Override
    public boolean unlock(Lock lock) {
        if (lock == null) {
            log.error("释放锁[]失败");
            return false;
        }
        if (StringUtils.isBlank(lock.getName())) {
            log.error("释放锁[{}]失败", lock.getLock());
            return false;
        }


        if (lock.getLock() != null) {
            try {
                ((InterProcessMutex) lock.getLock()).release();
            } catch (Exception e) {
                log.error("释放锁异常,{},error:", lock.getName(), e);
            }
            log.debug("释放锁成功");
            return true;
        }

        log.info("当前线程路径[{}]锁未创建,释放锁失败", lock.getName());
        return false;
    }

}
