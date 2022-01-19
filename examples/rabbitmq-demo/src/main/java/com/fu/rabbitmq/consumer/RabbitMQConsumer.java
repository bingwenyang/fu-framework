package com.fu.rabbitmq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RabbitMQConsumer {
    /**
     * 直连的接收
     *
     * @param msg
     */
    @RabbitListener(queues = "queue")
    public void consumer(Object msg) {
        log.info("queue消费消息-->{}", msg);
    }

    /**
     * 接收广播队列
     *
     * @param msg
     */
    @RabbitListener(queues = "queue_fanout01")
    public void consumer01(Object msg) {
        log.info("queue_fanout01消费消息-->{}", msg);
    }
    /**
     * 接收广播队列
     *
     * @param msg
     */
    @RabbitListener(queues = "queue_fanout02")
    public void consumer02(Object msg) {
        log.info("queue_fanout02消费消息-->{}", msg);
    }

    /**
     * 接收直连交换机队列队列
     *
     * @param msg
     */
    @RabbitListener(queues = "queue_direct01")
    public void consumer03(Object msg) {
        log.info("queue_direct01消费消息-->{}", msg);
    }
    /**
     * 接收直连交换机队列队列
     *
     * @param msg
     */
    @RabbitListener(queues = "queue_direct02")
    public void consumer04(Object msg) {
        log.info("queue_direct02消费消息-->{}", msg);
    }

    /**
     * 接收直连交换机队列队列
     *
     * @param msg
     */
    @RabbitListener(queues = "queue_topic01")
    public void consumer05(Object msg) {
        log.info("queue_topic01消费消息-->{}", msg);
    }

    /**
     * 接收直连交换机队列队列
     *
     * @param msg
     */
    @RabbitListener(queues = "queue_topic02")
    public void consumer06(Object msg) {
        log.info("queue_topic02消费消息-->{}", msg);
    }
}
