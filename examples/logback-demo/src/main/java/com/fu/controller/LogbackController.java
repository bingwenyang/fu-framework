/*
 * @(#) CecProjectController.java 2018/09/18
 *
 * Copyright 2018 CEC(Fujian) Healthcare Big Data Operation Service Co., Ltd. All rights reserved.
 */
package com.fu.controller;


import com.fu.core.model.Result;
import com.fu.service.LogbackImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping(value = "/v1")
public class LogbackController {

    @Autowired
    LogbackImpl logbackImpl;

    @GetMapping("/log")
    public Result<Boolean> getConfigInfo() {
        log.info("我是info主线程日志");
        log.debug("我是debug主线程日志");
        log.error("我是error主线程日志");
        log.trace("我是trace主线程日志");
        log.warn("我是warn主线程日志");
        logbackImpl.printServiceLog();
        return Result.ok(true);
    }

}


