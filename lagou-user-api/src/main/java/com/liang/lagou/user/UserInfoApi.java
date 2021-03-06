package com.liang.lagou.user;


import com.liang.lagou.pojo.LagouUser;
import com.liang.lagou.pojo.RestApiResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "lagou-user-service")
public interface UserInfoApi {

    @GetMapping("/user/info/{token}")
    RestApiResult<String> getEmailByToken(@PathVariable String token);

    @GetMapping("/user/info/byEmail/{email}")
    RestApiResult<LagouUser> getUserByEmail(@PathVariable String email);
}
