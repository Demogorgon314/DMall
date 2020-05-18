package com.deepdream.dmall.thirdparty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class DmallThirdPartyApplication {

    public static void main(String[] args) {
        SpringApplication.run(DmallThirdPartyApplication.class, args);
    }

}
