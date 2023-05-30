package com.fu.nettywebsocket.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 异步线程tracid测试
 */
@Service
@Slf4j
public class LogbackImpl {


    /**
     * 测试子类 是否获取父类的traceid
     *
     */
    @Async("analysisGlobalExecutor")
    public void printServiceLog() {
        log.info("我是子线程！");
    }
}
