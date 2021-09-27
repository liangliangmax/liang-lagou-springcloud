package com.liang.lagou.page;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PageBootstrapApplication {

    public static void main(String[] args) {
        SpringApplication.run(PageBootstrapApplication.class,args);
    }
}
