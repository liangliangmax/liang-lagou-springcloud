package com.liang.lagou.email;


import com.liang.lagou.pojo.RestApiResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "lagou-email-service",path = "/email")
public interface EmailApi {


    @GetMapping("/{email}/{code}")
    RestApiResult validateCode(@PathVariable("email") String email,@PathVariable("code") String code);


}
