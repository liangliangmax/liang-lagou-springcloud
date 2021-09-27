package com.liang.lagou.user;


import com.liang.lagou.pojo.RestApiResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "lagou-user-service",path = "/user")
public interface RegisterApi {


    @GetMapping("/register/{email}/{password}/{code}")
    RestApiResult register(@PathVariable("email") String email,@PathVariable("password")String password,@PathVariable("code")String code);



    @GetMapping("/isRegistered/{email}")
    RestApiResult isRegistered(@PathVariable("email") String email);


}
