package com.fu.redis.config;


import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: 配置redission用于分布式锁
 */
@Configuration
public class RedissionConfig {

    @Value("${spring.redis.host}")
    private  String host;

    @Value("${spring.redis.password}")
    private  String password;

    @Bean
    public RedissonClient getRedisson(){

        String h = "redis://"+host+":6379";
        RedissonClient redisson = null;
        Config config = new Config();
        config.useSingleServer()
                .setAddress(h)
                .setPassword(password)
                .setConnectionMinimumIdleSize(10);
        redisson = Redisson.create(config);

        return redisson;
    }

}
