package com.liang.lagou.user.controller;

import com.liang.lagou.pojo.LagouToken;
import com.liang.lagou.pojo.LagouUser;
import com.liang.lagou.pojo.RestApiResult;
import com.liang.lagou.pojo.ResultCode;
import com.liang.lagou.user.UserInfoApi;
import com.liang.lagou.user.mapper.LagouTokenMapper;
import com.liang.lagou.user.mapper.LagouUserMapper;
import com.liang.lagou.user.service.IUserInfoService;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;


@RestController
public class UserInfoController implements UserInfoApi {


    @Autowired
    private IUserInfoService userInfoService;

    @Override
    public RestApiResult getEmailByToken(String token) {

        String emailByToken = userInfoService.getEmailByToken(token);

        if(StringUtils.isBlank(emailByToken)){

            return RestApiResult.ERROR(ResultCode.NO_SUCH_RESULT);
        }

        return RestApiResult.OK("成功",emailByToken);
    }



    @Override
    public RestApiResult getUserByEmail(String email) {

        LagouUser lagouUser = userInfoService.getUserByEmail(email);

        if(lagouUser == null){
            return RestApiResult.ERROR(ResultCode.NO_SUCH_RESULT);
        }

        return RestApiResult.OK(lagouUser);
    }
}
