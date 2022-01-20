package com.fu.redis.config;

import com.fu.core.constant.GlobalConstants;
import com.fu.core.dlock.service.impl.DistributedLockService;
import com.fu.redis.lock.RedisDistributedLockServiceImpl;
import org.redisson.api.RedissonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {


    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        // 如果不设置则是默认jdk的序列化方式，也就是二进制的
        // 普通类型序列化
        // string序列化
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        // json字符串的通用序列化
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        // hash类型序列化
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());

        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }

    /**
     * 优先使用ZK分布式锁
     *
     * @return
     */
    @Bean
    public RedisDistributedLockServiceImpl redisDistributedLock(RedissonClient redisUtils) {
        return new RedisDistributedLockServiceImpl(redisUtils);
    }


    /**
     * @param redisDistributedLockServiceImpl
     * @return
     */
    @Bean(name = GlobalConstants.REDIS)
    public DistributedLockService distributedLockService(RedisDistributedLockServiceImpl redisDistributedLockServiceImpl) {
        return new DistributedLockService(redisDistributedLockServiceImpl);
    }

}
