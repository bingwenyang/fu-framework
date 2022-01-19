package com.fu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * alibaba seata 分布式事务
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan({"com.fu.dao*"})
public class SeataApp {
    public static void main(String[] args) {
        SpringApplication.run(SeataApp.class, args);
    }
}
