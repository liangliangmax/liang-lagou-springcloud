package com.liang.lagou.user.service.impl;

import com.liang.lagou.pojo.LagouUser;
import com.liang.lagou.user.mapper.LagouUserMapper;
import com.liang.lagou.user.service.IRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

@Service
public class RegisterServiceImpl implements IRegisterService {


    @Autowired
    private LagouUserMapper lagouUserMapper;

    @Override
    public int register(String email, String password) {

        //注册过了就不注册了
        if(isRegister(email)){

            return 2;
        }

        //进行注册
        LagouUser lagouUser = new LagouUser();
        lagouUser.setEmail(email);
        lagouUser.setPassword(password);

        return lagouUserMapper.insertSelective(lagouUser);

    }

    @Override
    public boolean isRegister(String email) {

        Example example = new Example(LagouUser.class);
        example.createCriteria().andEqualTo("email",email);

        LagouUser lagouUser = lagouUserMapper.selectOneByExample(example);

        if(lagouUser!=null)
            return true;

        return false;
    }
}
