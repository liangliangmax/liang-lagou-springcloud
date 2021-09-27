package com.liang.lagou.user;


import com.liang.lagou.pojo.RestApiResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "lagou-user-service",path = "/user")
public interface UserInfoApi {

    @GetMapping("/info/{token}")
    RestApiResult getEmailByToken(@PathVariable String token);

}
