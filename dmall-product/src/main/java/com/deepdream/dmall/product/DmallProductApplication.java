package com.deepdream.dmall.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@MapperScan("com.deepdream.dmall.product.dao")
@EnableFeignClients(basePackages = {"com.deepdream.dmall.product.feign"})
@EnableDiscoveryClient
@SpringBootApplication
public class DmallProductApplication {
    public static void main(String[] args) {
        SpringApplication.run(DmallProductApplication.class, args);
    }
}
