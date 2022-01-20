package com.fu.controller;

import com.fu.rabbitmq.producer.RabbitMQProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RabbitMQController {

    @Autowired
    RabbitMQProducer rabbitMQProducer;

    @PostMapping("send")
    public void  sendMsg() {
        rabbitMQProducer.sendQueue("发送消息");
    }

}
