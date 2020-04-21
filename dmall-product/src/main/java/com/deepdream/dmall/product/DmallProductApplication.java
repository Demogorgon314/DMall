package com.deepdream.dmall.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@MapperScan("com.deepdream.dmall.product.dao")
@EnableDiscoveryClient
@SpringBootApplication
public class DmallProductApplication {
    public static void main(String[] args) {
        SpringApplication.run(DmallProductApplication.class, args);
    }
}
