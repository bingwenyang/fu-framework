package com.fu.nettywebsocket.service;

import java.util.concurrent.TimeUnit;


public interface IZKDistributedLockService {

    /**
     * 分布式锁服务
     *
     * @param lockName           加锁名称
     * @param acquireLockTimeout 等待锁超时时间
     * @param unit               时间格式
     */
    void testDistributedLockService(String lockName, Long acquireLockTimeout, TimeUnit unit);

}
