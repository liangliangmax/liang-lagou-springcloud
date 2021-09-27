package com.liang.lagou.user;


import com.liang.lagou.pojo.RestApiResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "lagou-user-service")
public interface RegisterApi {


    @GetMapping("/user/register/{email}/{password}/{code}")
    RestApiResult register(@PathVariable("email") String email,@PathVariable("password")String password,@PathVariable("code")String code);



    @GetMapping("/user/isRegistered/{email}")
    RestApiResult isRegistered(@PathVariable("email") String email);


}
