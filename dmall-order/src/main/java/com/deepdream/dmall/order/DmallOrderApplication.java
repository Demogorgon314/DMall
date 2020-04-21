package com.deepdream.dmall.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@MapperScan("com.deepdream.dmall.order.dao")
@EnableDiscoveryClient
@SpringBootApplication
public class DmallOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(DmallOrderApplication.class, args);
    }

}
