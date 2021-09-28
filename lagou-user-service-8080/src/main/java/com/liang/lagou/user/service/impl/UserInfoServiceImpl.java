package com.liang.lagou.user.service.impl;

import com.liang.lagou.pojo.LagouToken;
import com.liang.lagou.pojo.LagouUser;
import com.liang.lagou.user.mapper.LagouTokenMapper;
import com.liang.lagou.user.mapper.LagouUserMapper;
import com.liang.lagou.user.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

@Service
public class UserInfoServiceImpl implements IUserInfoService {

    @Autowired
    private LagouTokenMapper lagouTokenMapper;

    @Autowired
    private LagouUserMapper lagouUserMapper;

    @Override
    public String getEmailByToken(String token) {

        Example example = new Example(LagouToken.class);

        example.createCriteria().andEqualTo("token",token);

        LagouToken lagouToken = lagouTokenMapper.selectOneByExample(example);

        if(lagouToken != null){
            return lagouToken.getEmail();
        }

        return null;
    }

    @Override
    public LagouUser getUserByEmail(String email) {

        Example example = new Example(LagouUser.class);

        example.createCriteria().andEqualTo("email",email);

        LagouUser lagouUser = lagouUserMapper.selectOneByExample(example);

        if(lagouUser == null){
            return null;
        }

        lagouUser.setPassword(null);

        return lagouUser;
    }
}
