package com.fu.nettywebsocket.controller;

import com.fu.core.model.Result;
import com.fu.nettywebsocket.service.HelloService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController

public class DuConsumerController {

    @DubboReference
    private HelloService helloService;


    @GetMapping("test/dubbo")
    public Result testDubboService() {
        return Result.ok(helloService.sayHello("consumer"));
    }

}
