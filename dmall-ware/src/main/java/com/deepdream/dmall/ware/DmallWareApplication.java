package com.deepdream.dmall.ware;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@MapperScan("com.deepdream.dmall.ware.dao")
@EnableDiscoveryClient
@SpringBootApplication
public class DmallWareApplication {

    public static void main(String[] args) {
        SpringApplication.run(DmallWareApplication.class, args);
    }

}
