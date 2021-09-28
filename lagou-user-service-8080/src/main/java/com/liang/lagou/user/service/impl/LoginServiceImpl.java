package com.liang.lagou.user.service.impl;

import com.liang.lagou.pojo.LagouUser;
import com.liang.lagou.user.mapper.LagouTokenMapper;
import com.liang.lagou.user.mapper.LagouUserMapper;
import com.liang.lagou.user.service.ILoginService;
import com.liang.lagou.user.service.ITokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

@Service
public class LoginServiceImpl implements ILoginService {


    @Autowired
    private LagouUserMapper lagouUserMapper;

    @Autowired
    private ITokenService tokenService;

    @Override
    public String login(String email, String password){

        Example example = new Example(LagouUser.class);

        example.createCriteria().andEqualTo("email",email);

        LagouUser lagouUser = lagouUserMapper.selectOneByExample(example);

        if(lagouUser == null)
            return null;

        if(!password.equalsIgnoreCase(lagouUser.getPassword()))
            return null;


        lagouUser.setPassword(null);

        String token = tokenService.createToken(lagouUser);

        return token;

    }

}
