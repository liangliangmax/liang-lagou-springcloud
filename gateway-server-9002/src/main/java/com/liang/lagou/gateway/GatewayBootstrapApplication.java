package com.liang.lagou.gateway;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableFeignClients(basePackages = "com.liang.lagou")
@SpringBootApplication(scanBasePackages = "com.liang.lagou")
public class GatewayBootstrapApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayBootstrapApplication.class,args);
    }
}
