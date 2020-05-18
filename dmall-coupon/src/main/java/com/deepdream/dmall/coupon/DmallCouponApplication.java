package com.deepdream.dmall.coupon;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@MapperScan("com.deepdream.dmall.coupon.dao")
//@EnableFeignClients(basePackages = )
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class DmallCouponApplication {

    public static void main(String[] args) {
        SpringApplication.run(DmallCouponApplication.class, args);
    }

}
