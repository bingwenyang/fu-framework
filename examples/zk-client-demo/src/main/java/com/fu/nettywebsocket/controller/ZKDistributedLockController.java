package com.fu.nettywebsocket.controller;

import com.fu.core.model.Result;
import com.fu.nettywebsocket.service.IZKDistributedLockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;


@RestController

public class ZKDistributedLockController {

    @Autowired
    IZKDistributedLockService distributedLockService;


    /**
     * 测试分布式锁
     *
     * @param lockName           锁名称
     * @param acquireLockTimeout 等待锁超时时间
     * @param unit               时间格式
     * @return
     */
    @GetMapping("test/ilockService")
    public Result testDistributedLockService(String lockName, Long acquireLockTimeout, TimeUnit unit) {
        distributedLockService.testDistributedLockService(lockName, acquireLockTimeout, unit);
        return Result.ok();
    }

}
