package com.fu.kafka.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * 生产者
 *
 */
@Component
public class KafkaProducer {
    @Autowired
    private KafkaTemplate kafkaTemplate;

    /**
     * kafka生产者
     *
     * @param topic
     * @param massage
     */
    public boolean sendMsg(String topic, Object massage) {
        /** 返回kafka是否返回成功*/
        boolean result = !kafkaTemplate.send(topic, massage).isDone();
        return result;
    }
}
