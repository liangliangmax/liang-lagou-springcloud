package com.liang.lagou.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServerBootstrapApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerBootstrapApplication.class,args);
    }
}
