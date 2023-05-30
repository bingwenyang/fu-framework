package com.fu.kafka.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;

/**
 * 消费者模式时开启
 */
@ConditionalOnProperty(name = {"spring.kafka.consumer.bootstrap-servers"})
@Configuration
@EnableKafka
public class KafkaConfig {

}
