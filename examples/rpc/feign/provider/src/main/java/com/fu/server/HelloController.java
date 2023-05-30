package com.fu.server;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping

public class HelloController {

    @GetMapping("/hello")
    public String sayHello(String name) {
        return "hello " + name;
    }
}
