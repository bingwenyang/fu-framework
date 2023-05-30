/*
 * @(#) CecProjectController.java 2018/09/18
 *
 * Copyright 2018 CEC(Fujian) Healthcare Big Data Operation Service Co., Ltd. All rights reserved.
 */
package com.fu.nettywebsocket.controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.fu.util.ExceptionUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/v1/sentinel")
@RefreshScope
public class SentinelController {
    // nacos 配置测试
    @Value("${config.info}")
    private String configInfo;


    /**
     * 接口限流 配置在sentinel,限流后调用ExceptionUtil中的handleExceptionStr方法
     *
     * @return
     */
    @SentinelResource(value = "getConfigInfo", blockHandler = "handleExceptionStr", blockHandlerClass = {ExceptionUtil.class})
    @GetMapping("/info")
    public String getConfigInfo() {
        return "config info : " + configInfo;
    }


}


