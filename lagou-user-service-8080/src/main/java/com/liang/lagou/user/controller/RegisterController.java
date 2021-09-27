package com.liang.lagou.user.controller;

import com.liang.lagou.pojo.RestApiResult;
import com.liang.lagou.user.RegisterApi;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RegisterController implements RegisterApi {


    @Override
    public RestApiResult register(String email, String password, String code) {
        return null;
    }

    @Override
    public RestApiResult isRegistered(String email) {
        return null;
    }
}
