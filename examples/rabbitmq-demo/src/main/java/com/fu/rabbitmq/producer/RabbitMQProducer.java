package com.fu.rabbitmq.producer;


import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RabbitMQProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    public void sendQueue(Object msg) {
        log.info("发送消息-->{}", msg);
//     直接发送到队列
        rabbitTemplate.convertAndSend("queue", msg);

    }

    /**
     * 消息发送到交换机 的所有队列
     *
     * @param msg
     */
    public void sendExchange(Object msg) {
        log.info("发送消息-->{}", msg);
        rabbitTemplate.convertAndSend("fanout_exchange", "", msg);

    }

    /**
     * 消息发送到交换机 的所有队列
     *
     * @param msg
     */
    public void sendDirectByKey(Object msg) {
        log.info("direct发送消息01-->{}", msg);
        rabbitTemplate.convertAndSend("direct_exchange", "queue.key01", msg);

    }

    /**
     * 消息发送到主题交换机1
     *
     * @param msg
     */
    public void sendTopicByKey01(Object msg) {
        log.info("topic发送消息01-->{}", msg);
        rabbitTemplate.convertAndSend("topic_exchange", "queue.key01.a", msg);

    }
    /**
     * 消息发送到主题交换机1
     *
     * @param msg
     */
    public void sendTopicByKey02(Object msg) {
        log.info("topic发送消息02-->{}", msg);
        rabbitTemplate.convertAndSend("topic_exchange", "key01.a.key02", msg);

    }
}
