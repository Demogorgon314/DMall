package com.deepdream.dmall.member;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
@EnableDiscoveryClient
@MapperScan("com.deepdream.dmall.member.dao")
@EnableFeignClients("com.deepdream.dmall.member.feign")
@SpringBootApplication
public class DmallMemberApplication {

    public static void main(String[] args) {
        SpringApplication.run(DmallMemberApplication.class, args);
    }

}
