package com.fu.redis.lock;

import com.fu.core.dlock.entity.Lock;
import com.fu.core.dlock.service.IDistributedLockService;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;


public class RedisDistributedLockServiceImpl implements IDistributedLockService {

    private static final Logger log = LoggerFactory.getLogger(RedisDistributedLockServiceImpl.class);

    private final RedissonClient redissonClient;

    public RedisDistributedLockServiceImpl(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }


    @Override
    public Lock newLock(String lockName) {
        Lock redisLock = new Lock(lockName);
        return redisLock;
    }


    @Override
    public boolean tryLock(Lock lock) {
        return tryLock(lock, -1);
    }

    @Override
    public boolean tryLock(Lock lock, long acquireTimeoutMs) {
        RLock rLock = redissonClient.getLock(lock.getName());
        try {
            // 有watchdog
            return rLock.tryLock(acquireTimeoutMs <= 0 ? -1 : acquireTimeoutMs, acquireTimeoutMs <= 0 ? null : TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            return false;
        }
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


        if (StringUtils.isNotEmpty(lock.getName())) {
            try {
                RLock rlock = redissonClient.getLock(lock.getName());
                rlock.unlock();
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
