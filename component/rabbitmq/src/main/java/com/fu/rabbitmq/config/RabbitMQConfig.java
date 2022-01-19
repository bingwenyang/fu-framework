package com.fu.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    private static final String QUEUE01 = "queue_fanout01";
    private static final String QUEUE02 = "queue_fanout02";
    private static final String EXCHANGE = "fanout_exchange";

    private static final String QUEUE03 = "queue_direct01";
    private static final String QUEUE04 = "queue_direct02";
    private static final String EXCHANGE02 = "direct_exchange";
    private static final String ROUTINGKEY01 = "queue.key01";
    private static final String ROUTINGKEY02 = "queue.key02";


    private static final String QUEUE05 = "queue_topic01";
    private static final String QUEUE06 = "queue_topic02";
    private static final String EXCHANGE03 = "topic_exchange";
    private static final String ROUTINGKEY03 = "#.a.#";
    private static final String ROUTINGKEY04 = "*.a.#";
    /**
     * 基础队列
     */
    @Bean
    public Queue queue() {
        // 队列配置持久化
        return new Queue("queue", true);
    }

    /**
     * 广播队列01
     */
    @Bean
    public Queue queue01() {

        return new Queue(QUEUE01);
    }

    /**
     * 广播队列02
     */
    @Bean
    public Queue queue02() {
        return new Queue(QUEUE02);
    }
    /**
     * 直连队列01
     */
    @Bean
    public Queue queue03() {

        return new Queue(QUEUE03);
    }

    /**
     * 直连队列02
     */
    @Bean
    public Queue queue04() {
        return new Queue(QUEUE04);
    }

    /**
     * 直连队列01
     */
    @Bean
    public Queue queue05() {

        return new Queue(QUEUE05);
    }

    /**
     * 直连队列02
     */
    @Bean
    public Queue queue06() {
        return new Queue(QUEUE06);
    }
    /**
     * 广播交换机
     *
     * @return
     */
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(EXCHANGE);
    }

    /**
     * 广播交换机
     *
     * @return
     */
    @Bean
    public DirectExchange  directExchange() {
        return new DirectExchange(EXCHANGE02);
    }

    /**
     * 广播交换机
     *
     * @return
     */
    @Bean
    public TopicExchange  topicExchange() {
        return new TopicExchange(EXCHANGE03);
    }
    /**
     * 绑定01队列到交换机上
     *
     * @return
     */
    @Bean
    public Binding binding01() {
        return BindingBuilder.bind(queue01()).to(fanoutExchange());
    }

    /**
     * 绑定02队列到交换机上
     *
     * @return
     */
    @Bean
    public Binding binding02() {
        return BindingBuilder.bind(queue02()).to(fanoutExchange());
    }

    /**
     * 绑定03队列到direct交换机使用01路由键
     *
     * @return
     */
    @Bean
    public Binding binding03() {
        return BindingBuilder.bind(queue03()).to(directExchange()).with(ROUTINGKEY01);
    }
    /**
     * 绑定03队列到direct交换机
     *
     * @return
     */
    @Bean
    public Binding binding04() {
        return BindingBuilder.bind(queue04()).to(directExchange()).with(ROUTINGKEY02);
    }


    /**
     * 绑定03队列到direct交换机使用01路由键
     *
     * @return
     */
    @Bean
    public Binding binding05() {
        return BindingBuilder.bind(queue05()).to(topicExchange()).with(ROUTINGKEY03);
    }
    /**
     * 绑定03队列到direct交换机
     *
     * @return
     */
    @Bean
    public Binding binding06() {
        return BindingBuilder.bind(queue06()).to(topicExchange()).with(ROUTINGKEY04);
    }
}
