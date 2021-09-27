package com.liang.lagou.user.controller;

import com.liang.lagou.pojo.RestApiResult;
import com.liang.lagou.user.UserInfoApi;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserInfoController implements UserInfoApi {



    @Override
    public RestApiResult getEmailByToken(String token) {
        return null;
    }
}
