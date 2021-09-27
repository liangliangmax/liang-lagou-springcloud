package com.liang.lagou.user.controller;

import com.liang.lagou.pojo.RestApiResult;
import com.liang.lagou.user.LoginApi;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController implements LoginApi {


    @Override
    public RestApiResult register(String email, String password) {
        return null;
    }
}
