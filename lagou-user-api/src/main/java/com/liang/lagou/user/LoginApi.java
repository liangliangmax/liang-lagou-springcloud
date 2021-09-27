package com.liang.lagou.user;


import com.liang.lagou.pojo.RestApiResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "lagou-user-service")
public interface LoginApi {

    @GetMapping("/user/login/{email}/{password}")
    RestApiResult register(@PathVariable("email") String email,@PathVariable("password")String password);

}
