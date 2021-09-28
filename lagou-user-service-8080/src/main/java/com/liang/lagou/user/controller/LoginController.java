package com.liang.lagou.user.controller;

import com.liang.lagou.pojo.LagouUser;
import com.liang.lagou.pojo.RestApiResult;
import com.liang.lagou.pojo.ResultCode;
import com.liang.lagou.user.LoginApi;
import com.liang.lagou.user.service.ILoginService;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class LoginController implements LoginApi {

    @Autowired
    private ILoginService loginService;


    @Override
    public RestApiResult login(String email, String password, HttpServletRequest request, HttpServletResponse response) {

        String token = loginService.login(email, password);

        if(StringUtils.isBlank(token)){

            return RestApiResult.ERROR(ResultCode.NO_SUCH_RESULT,"用户名或密码错误",false);
        }
        
        return RestApiResult.OK("成功",token);
    }
}
