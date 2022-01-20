/*
 * @(#) CecProjectController.java 2018/09/18
 *
 * Copyright 2018 CEC(Fujian) Healthcare Big Data Operation Service Co., Ltd. All rights reserved.
 */
package com.fu.controller;


import com.fu.entity.Dept;
import com.fu.service.DeptService;
import io.seata.core.exception.TransactionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(value = "/v1/seata-first")
@RefreshScope
public class SeataController {
    // nacos 配置测试
    @Value("${config.info}")
    private String configInfo;

    @Autowired
    private DeptService service;

    /**
     * 执行添加dept操作测试seata
     *
     * @param dept
     * @return
     * @throws TransactionException
     */
    @RequestMapping(value = "/dept/add", method = RequestMethod.POST)
    public List<Dept> add(@RequestBody Dept dept) throws TransactionException {
        return service.add(dept);
    }
}


