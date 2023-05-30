package com.fu.nettywebsocket.service.controller;

import com.fu.kafka.config.KafkaProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class KafKaController {

    @Autowired
    KafkaProducer kafkaProducer;

    String topic = "hello";

    @PutMapping("kafka")
    void sendMsg(String body) {

        Boolean res = kafkaProducer.sendMsg(topic, body);
        log.info("是否发送成功:{}", res);
    }


    @KafkaListener(topics = "hello")
    void getMsg(String msg) {
        log.info("收到消息：{}", msg);
    }
}
