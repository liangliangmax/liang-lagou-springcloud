package com.liang.lagou.user.service.impl;

import com.liang.lagou.pojo.LagouToken;
import com.liang.lagou.pojo.LagouUser;
import com.liang.lagou.user.mapper.LagouTokenMapper;
import com.liang.lagou.user.service.ITokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.UUID;

@Service
public class TokenServiceImpl implements ITokenService {

    @Autowired
    private LagouTokenMapper lagouTokenMapper;


    @Override
    public String createToken(LagouUser lagouUser) {

        String token = UUID.randomUUID().toString();

        LagouToken lagouToken = new LagouToken();

        lagouToken.setEmail(lagouUser.getEmail());
        lagouToken.setToken(token);

        Example example = new Example(LagouToken.class);
        example.createCriteria().andEqualTo("email",lagouUser.getEmail());

        LagouToken lagouTokenDb = lagouTokenMapper.selectOneByExample(example);

        if(lagouTokenDb == null){
            lagouTokenMapper.insertSelective(lagouToken);
        }else {

            lagouTokenDb.setToken(token);

            lagouTokenMapper.updateByPrimaryKey(lagouTokenDb);

        }

        return token;
    }
}
