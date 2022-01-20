package com.fu.zk.config;

import com.fu.core.constant.GlobalConstants;
import com.fu.core.dlock.service.impl.DistributedLockService;
import com.fu.zk.client.ZookeeperClient;
import com.fu.zk.lock.ZKDistributedLockServiceImpl;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @Description curator配置类
 */
@Configuration
@EnableConfigurationProperties(CuratorProperties.class)
@ConditionalOnProperty(name = {"spring.curator.connectString"})
public class CuratorConfig {


    /**
     * 初始化CuratorFramework 实例
     *
     * @param curatorProperties
     * @param applicationName   应用名
     * @return
     */
    @Bean(initMethod = "start")
    @ConditionalOnMissingBean
    public CuratorFramework curatorFramework(CuratorProperties curatorProperties, @Value("${spring.application.name}") String applicationName) {

        return CuratorFrameworkFactory.builder().connectString(curatorProperties.getConnectString())
                .sessionTimeoutMs(curatorProperties.getSessionTimeoutMs())
                .connectionTimeoutMs(curatorProperties.getConnectionTimeoutMs())
                .retryPolicy(new RetryNTimes(curatorProperties.getRetryCount(), curatorProperties.getElapsedTimeMs()))
                .namespace(applicationName + "-node")
                .build();

    }

    @Bean
    @ConditionalOnMissingBean
    public ZookeeperClient zookeeperClient(CuratorFramework curatorFramework) {
        return new ZookeeperClient(curatorFramework);
    }


    /**
     * 优先使用ZK分布式锁
     *
     * @return
     */
    @Bean
    public ZKDistributedLockServiceImpl zkDistributedLock(CuratorFramework curatorFramework) {
        return new ZKDistributedLockServiceImpl(curatorFramework);
    }


    /**
     * @param zkDistributedLockServiceImpl
     * @return
     */
    @Bean(name = GlobalConstants.ZOOKEEPER)
    @Primary
    public DistributedLockService distributedLockService(ZKDistributedLockServiceImpl zkDistributedLockServiceImpl) {
        return new DistributedLockService(zkDistributedLockServiceImpl);
    }


}
