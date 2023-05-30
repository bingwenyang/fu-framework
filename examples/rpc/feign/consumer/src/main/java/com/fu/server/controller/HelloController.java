package com.fu.server.controller;

import com.fu.server.service.IHelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping
public class HelloController {

    @Autowired
    IHelloService helloService;

    @GetMapping("/hello")
    public String sayHello(String name) {
        return "测试远程调用：" + helloService.sayHello(name);
    }
}
