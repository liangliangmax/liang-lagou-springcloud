package com.liang.lagou.code;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class CodeServerBootstrapApplication {

    public static void main(String[] args) {
        SpringApplication.run(CodeServerBootstrapApplication.class,args);
    }
}
