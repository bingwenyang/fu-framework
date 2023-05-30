package com.fu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
public class ProvideApp {
    public static void main(String[] args) {
        SpringApplication.run(ProvideApp.class, args);
    }
}
