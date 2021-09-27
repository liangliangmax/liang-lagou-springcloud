package com.liang.lagou.user.controller;

import com.liang.lagou.pojo.RestApiResult;
import com.liang.lagou.user.RegisterApi;
import com.liang.lagou.user.service.IRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RegisterController implements RegisterApi {

    @Autowired
    private IRegisterService registerService;

    @Override
    public RestApiResult register(String email, String password, String code) {

        int register = registerService.register(email, password);

        return RestApiResult.OK(register);
    }

    @Override
    public RestApiResult isRegistered(String email) {

        boolean register = registerService.isRegister(email);

        return RestApiResult.OK(register);
    }
}
