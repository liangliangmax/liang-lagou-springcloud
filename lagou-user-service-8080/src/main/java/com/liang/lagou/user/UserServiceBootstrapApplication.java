package com.liang.lagou.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class UserServiceBootstrapApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceBootstrapApplication.class,args);
    }
}
