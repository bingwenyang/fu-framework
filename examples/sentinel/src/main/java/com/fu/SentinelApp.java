package com.fu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * alibaba sentinel 限流
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
public class SentinelApp {
    public static void main(String[] args) {
        SpringApplication.run(SentinelApp.class, args);
    }
}
