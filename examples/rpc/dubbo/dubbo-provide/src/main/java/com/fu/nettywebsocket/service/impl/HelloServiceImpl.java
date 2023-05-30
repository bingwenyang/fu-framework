package com.fu.nettywebsocket.service.impl;

import com.fu.nettywebsocket.service.HelloService;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        return "hello " + name;
    }
}
