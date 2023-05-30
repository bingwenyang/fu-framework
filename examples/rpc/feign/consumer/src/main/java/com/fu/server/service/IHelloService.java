package com.fu.server.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

//@FeignClient(name = "provider",url = "http://localhost:7004") // 可以不注册，直接调用
@FeignClient("provider")
public interface IHelloService {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    String sayHello(@RequestParam String name);
}
