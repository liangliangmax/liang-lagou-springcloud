package com.liang.lagou.code;


import com.liang.lagou.pojo.RestApiResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "lagou-code-service")
public interface CodeApi {


    @GetMapping("/code/create/{email}")
    RestApiResult generateCode(@PathVariable String email);


    @GetMapping("/code/validate/{email}/{code}")
    RestApiResult validateCode(@PathVariable("email") String email,@PathVariable("code") String code);


}
