package com.liang.lagou.user.controller;

import com.liang.lagou.pojo.LagouToken;
import com.liang.lagou.pojo.RestApiResult;
import com.liang.lagou.pojo.ResultCode;
import com.liang.lagou.user.UserInfoApi;
import com.liang.lagou.user.mapper.LagouTokenMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;


@RestController
public class UserInfoController implements UserInfoApi {

    @Autowired
    private LagouTokenMapper lagouTokenMapper;

    @Override
    public RestApiResult getEmailByToken(String token) {

        Example example = new Example(LagouToken.class);

        example.createCriteria().andEqualTo("token",token);

        LagouToken lagouToken = lagouTokenMapper.selectOneByExample(example);

        if(lagouToken == null){

            return RestApiResult.ERROR(ResultCode.NO_SUCH_RESULT);
        }
        
        return RestApiResult.OK("成功",lagouToken.getEmail());
    }
}
